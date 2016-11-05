var wrapper;
EXPORT_WIDTH = 1000;
var data;
var tableImage;
(function () {
    var callWithJQuery;

    callWithJQuery = function (pivotModule) {
        if (typeof exports === "object" && typeof module === "object") {
            return pivotModule(require("jquery"));
        } else if (typeof define === "function" && define.amd) {
            return define(["jquery"], pivotModule);
        } else {
            return pivotModule(jQuery);
        }
    };

    callWithJQuery(function ($) {
        var makeGoogleChart;
        makeGoogleChart = function (chartType, extraOptions) {
            return function (pivotData, opts) {
                var agg, colKey, colKeys, dataArray, dataTable, defaults, fullAggName, groupByTitle, h, hAxisTitle, headers, numCharsInHAxis, options, result, row, rowKey, rowKeys, title, tree2, vAxisTitle, val, x, y, _base, _base1, _i, _j, _len, _len1, _ref, _ref1, _ref2;
                defaults = {
                    localeStrings: {
                        vs: "vs",
                        by: "by"
                    },
                    gchart: {}
                };
                opts = $.extend(true, defaults, opts);
                if ((_ref = (_base = opts.gchart).width) == null) {
                    _base.width = window.innerWidth / 1.5;
                }
                if ((_ref1 = (_base1 = opts.gchart).height) == null) {
                    _base1.height = window.innerHeight / 1.4;
                }
                rowKeys = pivotData.getRowKeys();
                if (rowKeys.length === 0) {
                    rowKeys.push([]);
                }
                colKeys = pivotData.getColKeys();
                if (colKeys.length === 0) {
                    colKeys.push([]);
                }
                fullAggName = pivotData.aggregatorName;
                if (pivotData.valAttrs.length) {
                    fullAggName += "(" + (pivotData.valAttrs.join(", ")) + ")";
                }
                headers = (function () {
                    var _i, _len, _results;
                    _results = [];
                    for (_i = 0, _len = rowKeys.length; _i < _len; _i++) {
                        h = rowKeys[_i];
                        _results.push(h.join("-"));
                    }
                    return _results;

                })();
                headers.unshift("");
                numCharsInHAxis = 0;
                if (chartType === "ScatterChart") {
                    dataArray = [];
                    _ref2 = pivotData.tree;
                    for (y in _ref2) {
                        tree2 = _ref2[y];
                        for (x in tree2) {
                            agg = tree2[x];
                            dataArray.push([parseFloat(x), parseFloat(y), fullAggName + ": \n" + agg.format(agg.value())]);
                        }
                    }
                    dataTable = new google.visualization.DataTable();
                    dataTable.addColumn('number', pivotData.colAttrs.join("-"));
                    dataTable.addColumn('number', pivotData.rowAttrs.join("-"));
                    dataTable.addColumn({
                        type: "string",
                        role: "tooltip"
                    });
                    dataTable.addRows(dataArray);
                    hAxisTitle = pivotData.colAttrs.join("-");
                    vAxisTitle = pivotData.rowAttrs.join("-");
                    title = "";
                } else {
                    dataArray = [headers];
                    for (_i = 0, _len = colKeys.length; _i < _len; _i++) {
                        colKey = colKeys[_i];
                        row = [colKey.join("-")];
                        numCharsInHAxis += row[0].length;
                        for (_j = 0, _len1 = rowKeys.length; _j < _len1; _j++) {
                            rowKey = rowKeys[_j];

                            agg = pivotData.getAggregator(rowKey, colKey);
                            if (agg.value() != null) {
                                val = agg.value();
                                if ($.isNumeric(val)) {
                                    if (val < 1) {
                                        row.push(parseFloat(val.toPrecision(3)));
                                    } else {
                                        row.push(parseFloat(val.toFixed(3)));
                                    }
                                } else {
                                    row.push(val);
                                }
                            } else {
                                row.push(null);
                            }
                        }
                        dataArray.push(row);
                    }
                    dataTable = google.visualization.arrayToDataTable(dataArray);
                    title = vAxisTitle = fullAggName;
                    hAxisTitle = pivotData.colAttrs.join("-");
                    if (hAxisTitle !== "") {
                        title += " " + opts.localeStrings.vs + " " + hAxisTitle;
                    }
                    groupByTitle = pivotData.rowAttrs.join("-");
                    if (groupByTitle !== "") {
                        title += " " + opts.localeStrings.by + " " + groupByTitle;
                    }
                }
                options = {
                    title: title,
                    hAxis: {
                        viewWindow: {min: 0, max: 5},
                        title: hAxisTitle,
                        slantedText: numCharsInHAxis > 50
                    },
                    vAxis: {
                        title: vAxisTitle
                    },
                    tooltip: {
                        textStyle: {
                            fontName: 'Arial',
                            fontSize: 10
                        }
                    },
                    width: 50000,
                    height: 400,
                    animation: {
                        duration: 1000,
                        easing: 'in'
                    }, chartArea: {width: '50%'}
                };
                if (chartType === "ColumnChart") {
                    options.vAxis.minValue = 0;
                }
                if (chartType === "ScatterChart") {
                    options.legend = {
                        position: "none"
                    };
                    options.chartArea = {
                        'width': '50000%',
                        'height': '50%'
                    };
                } else if (dataArray[0].length === 2 && dataArray[0][1] === "") {
                    options.legend = {
                        position: "none"
                    };
                }
                $.extend(options, opts.gchart, extraOptions);
                result = $("<div>").css({
                    width: "100%",
                    height: "100%"
                });
                var prevButton = document.getElementById('b1');
                var nextButton = document.getElementById('b2');
                var changeZoomButton = document.getElementById('b3');

                wrapper = new google.visualization.ChartWrapper({
                    dataTable: dataTable,
                    chartType: chartType,
                    options: options
                });
                var MAX = 10;

                prevButton.disabled = true;
                nextButton.disabled = true;
                changeZoomButton.disabled = true;

                google.visualization.events.addListener(wrapper, 'ready',
                        function () {
                            prevButton.disabled = options.hAxis.viewWindow.min <= 0;
                            nextButton.disabled = options.hAxis.viewWindow.max >= MAX;
                            changeZoomButton.disabled = false;


                            $("#btnMatrix, #btnReport").unbind('click').click(function () {
                                document.getElementById('showReport').style.display = "block";

                                var conceptName = $('#commonReports').find(":selected").text();

                                if (conceptName != 'Population by year'
                                        && conceptName != 'Enrollment in Public and Private Schools'
                                        && conceptName != 'Actual number of Beds in Private and Public Hospitals') {

                                    if (theChartSelected === "Table") {
                                        //SVG
                                        if (this.id === "btnMatrix") {
                                            var para = document.createElement("div");
                                            var element = document.getElementById("reportBody");
                                            para.setAttribute("class", "reportContents");
                                            element.appendChild(para);
                                            $('.reportContents').append();
                                            $('.reportContents').append('<input id="title" style="border:none;" name="title" type="hidden" value=" ' + title + '"/>');
                                            chartImage();
                                            $('.reportContents').append('<input type="hidden" id="imageSrc" name = "imageSrc" value="' + tableImage + '"/>');
                                            $('.reportContents').append('<img style="width: 90%;" id="image" src="' + tableImage + '">');
                                            document.getElementById('image').setAttribute( 'src', tableImage);

                                            

                                            $('div#matrix')                         // grab the media content
                                                    .clone()                          // make a duplicate of it
                                                    .removeAttr('id')               // remove their ID attributes
                                                    .appendTo('.reportContents'); // now add it to the media container
                                            $('.reportContents').append('<button class="btn btn-danger btn-sm" type="button" onclick="deleteDivNotify(this)">Delete Chart</button>');
                                            $('.reportContents').append('<br><br><br><hr/>');
                                            para.setAttribute("class", "reportDelete");
                                        } else if (this.id === "btnReport") {
                                            var para = document.createElement("div");
                                            var element = document.getElementById("reportBody");
                                            para.setAttribute("class", "reportContents");
                                            element.appendChild(para);
                                            $('.reportContents').append('<input id="title" style="border:none;" name="title" type="hidden" value=" ' + title + '"/>');
                                            chartImage();
                                            $('.reportContents').append('<input type="hidden" id="imageSrc" name = "imageSrc" value="' + tableImage + '"/>');
                                            $('.reportContents').append('<img style="width: 90%;" id="image" src="">');
                                         document.getElementById('image').setAttribute( 'src', tableImage);
                                            $('div#report').clone().removeAttr('id').appendTo('.reportContents');
                                            $('.reportContents').append('<button class="btn btn-danger btn-sm" type="button" onclick="deleteDivNotify(this)">Delete Chart</button>');
                                            $('.reportContents').append('<br><br><br><hr/>');
                                            para.setAttribute("class", "reportDelete");
                                        }
                                    } else {

                                        //SVG
                                        var chart = $('#output').highcharts();
                                        var render_width = EXPORT_WIDTH;
                                        var render_height = render_width * chart.chartHeight / chart.chartWidth;

                                        // Get the cart's SVG code
                                        var svg = chart.getSVG({
                                            exporting: {
                                                sourceWidth: chart.chartWidth,
                                                sourceHeight: chart.chartHeight
                                            }
                                        });


                                        var image = new Image;
                                        var canvas = document.createElement('canvas');
                                        canvas.height = render_height;
                                        canvas.width = render_width;

                                        image.src = 'data:image/svg+xml;base64,' + window.btoa(unescape(encodeURIComponent(svg)));

                                        canvas.getContext('2d').drawImage(image, 0, 0, render_width, render_height);
                                        data = canvas.toDataURL("image/png");


                                        //SVG
                                        if (this.id === "btnMatrix") {
                                            var para = document.createElement("div");
                                            var element = document.getElementById("reportBody");
                                            para.setAttribute("class", "reportContents");
                                            element.appendChild(para);
                                            $('.reportContents').append();
                                            $('.reportContents').append('<input id="title" style="border:none;" name="title" type="hidden" value=" ' + title + '"/>');
                                            $('.reportContents').append('<img style="width: 90%;" id="image" src="' + image.src + '">');
                                            $('.reportContents').append('<input type="hidden" id="imageSrc" name = "imageSrc" value="' + data + '"/>');

                                            $('div#matrix')                         // grab the media content
                                                    .clone()                          // make a duplicate of it
                                                    .removeAttr('id')               // remove their ID attributes
                                                    .appendTo('.reportContents'); // now add it to the media container
                                            $('.reportContents').append('<button class="btn btn-danger btn-sm" type="button" onclick="deleteDivNotify(this)">Delete Chart</button>');
                                            $('.reportContents').append('<br><br><br><hr/>');
                                            para.setAttribute("class", "reportDelete");
                                        } else if (this.id === "btnReport") {
                                            var para = document.createElement("div");
                                            var element = document.getElementById("reportBody");
                                            para.setAttribute("class", "reportContents");
                                            element.appendChild(para);
                                            $('.reportContents').append('<input id="title" style="border:none;" name="title" type="hidden" value=" ' + title + '"/>');
                                            $('.reportContents').append('<img style="width: 90%;" id="image" src="' + image.src + '">');
                                            $('.reportContents').append('<input type="hidden" id="imageSrc" name = "imageSrc" value="' + data + '"/>');

                                            $('div#report').clone().removeAttr('id').appendTo('.reportContents');
                                            $('.reportContents').append('<button class="btn btn-danger btn-sm" type="button" onclick="deleteDivNotify(this)">Delete Chart</button>');
                                            $('.reportContents').append('<br><br><br><hr/>');
                                            para.setAttribute("class", "reportDelete");
                                        }
                                    }
                                } else {
                                    if (this.id === "btnMatrix") {
                                        var para = document.createElement("div");
                                        var element = document.getElementById("reportBody");
                                        para.setAttribute("class", "reportContents");
                                        element.appendChild(para);
                                        $('.reportContents').append();
                                        $('.reportContents').append('<input id="title" style="border:none;" name="title" type="hidden" value=" ' + title + '"/>');
                                        $('.reportContents').append('<img style="width: 90%;" id="image" src="' + wrapper.getChart().getImageURI() + '">');
                                        $('.reportContents').append('<input type="hidden" id="imageSrc"  name = "imageSrc" value="' + wrapper.getChart().getImageURI() + '"/>');

                                        console.log(wrapper.getChart().getImageURI());
                                        $('div#matrix')                         // grab the media content
                                                .clone()                          // make a duplicate of it
                                                .removeAttr('id')               // remove their ID attributes
                                                .appendTo('.reportContents'); // now add it to the media container
                                        $('.reportContents').append('<button class="btn btn-danger btn-sm" type="button" onclick="deleteDivNotify(this)">Delete Chart</button>');
                                        $('.reportContents').append('<br><br><br><hr/>');
                                        para.setAttribute("class", "reportDelete");
                                    } else if (this.id === "btnReport") {

                                        var para = document.createElement("div");
                                        var element = document.getElementById("reportBody");
                                        para.setAttribute("class", "reportContents");
                                        element.appendChild(para);
                                        $('.reportContents').append('<input id="title" style="border:none;" name="title" type="hidden" value=" ' + title + '"/>');
                                        $('.reportContents').append('<img style="width: 90%;" id="image" src="' + wrapper.getChart().getImageURI() + '">');
                                        $('.reportContents').append('<input type="hidden" id="imageSrc" name = "imageSrc" value="' + wrapper.getChart().getImageURI() + '"/>');
                                        console.log(wrapper.getChart().getImageURI());
                                        $('div#report').clone().removeAttr('id').appendTo('.reportContents');
                                        $('.reportContents').append('<button class="btn btn-danger btn-sm" type="button" onclick="deleteDivNotify(this)">Delete Chart</button>');
                                        $('.reportContents').append('<br><br><br><hr/>');
                                        para.setAttribute("class", "reportDelete");
                                    }
                                }
                            }
                            );
                        });
                wrapper.draw(result[0]);

                prevButton.onclick = function () {
                    options.hAxis.viewWindow.min -= 1;
                    options.hAxis.viewWindow.max -= 1;
                    wrapper.draw(result[0]);
                };
                nextButton.onclick = function () {
                    options.hAxis.viewWindow.min += 1;
                    options.hAxis.viewWindow.max += 1;
                    wrapper.draw(result[0]);
                };
                var zoomed = false;
                changeZoomButton.onclick = function () {
                    if (zoomed) {
                        options.hAxis.viewWindow.min = 0;
                        options.hAxis.viewWindow.max = 5;
                    } else {
                        options.hAxis.viewWindow.min = 0;
                        options.hAxis.viewWindow.max = MAX;
                    }
                    zoomed = !zoomed;
                    wrapper.draw(result[0]);

                };

                result.bind("dblclick", function () {
                    var editor;
                    editor = new google.visualization.ChartEditor();
                    google.visualization.events.addListener(editor, 'ok', function () {
                        wrapper = editor.getChartWrapper();
                        return wrapper.draw(result[0]);
                    });
                    return editor.openDialog(wrapper);

                });
                return result;


            }
            ;
        };

        return $.pivotUtilities.gchart_renderers = {
            "Line Chart": makeGoogleChart("LineChart"),
            "Bar Chart": makeGoogleChart("ColumnChart"),
            "Stacked Bar Chart": makeGoogleChart("ColumnChart", {
                isStacked: true
            }),
            "Area Chart": makeGoogleChart("AreaChart", {
                isStacked: true
            }),
            "Scatter Chart": makeGoogleChart("ScatterChart")
        };
    });

}).call(this);


function chartImage() {
    
    
    
    
    html2canvas(document.getElementById("dataTable"), {
        onrendered: function (canvas) {
            var img = canvas.toDataURL("image/png");
             tableImage = img;
             console.log(tableImage);
             
                 var image = new Image;
               var canvas = document.createElement('canvas');
               image.src = tableImage;
                                        canvas.getContext('2d').drawImage(image, 0, 0);
                                        tableImage = canvas.toDataURL("image/png");
        }
    });
}