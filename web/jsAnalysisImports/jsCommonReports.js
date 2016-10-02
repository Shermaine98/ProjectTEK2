function setHHPopAgeGroupSex (){
    $.ajax({
        url: "SetHHPopAgeGroupSex",
        type: 'POST',
        dataType: "JSON",
        success: function(data){
            var print = data;
            var malePerBarangay = [];
            PerBarangay = [];
            for (var i = 0; i < print[0].arrTotalMFFemale.length; i++) {
                item = {};
                item["name"] = print[0].arrTotalMFFemale[i].arrTotalMFlocation;
                item["y"] = print[0].arrTotalMFFemale[i].arrTotalMFMale;
                item["drilldown"] = print[0].arrTotalMFFemale[i].arrTotalMFlocation + 'm';
                malePerBarangay.push(item);
            }
            var femalePerBarangay = [];
            for (var i = 0; i < print[0].arrTotalMFFemale.length; i++) {
                item = {};
                item["name"] = print[0].arrTotalMFFemale[i].arrTotalMFlocation;
                item["y"] = print[0].arrTotalMFFemale[i].arrTotalMFFemale;
                item["drilldown"] = print[0].arrTotalMFFemale[i].arrTotalMFlocation + 'f';
                femalePerBarangay.push(item);
            }

            var malePerAgeGroup = [];
            for (var i = 0; i < print[0].totalAgeGroupSex.length; i++) {
                item = {};
                item["name"] = print[0].totalAgeGroupSex[i].ageGroup;
                item["y"] = -print[0].totalAgeGroupSex[i].male;
                item["drilldown"] = print[0].totalAgeGroupSex[i].ageGroup + 'm';
                malePerAgeGroup.push(item);
            }
            var femalePerAgeGroup = [];
            for (var i = 0; i < print[0].totalAgeGroupSex.length; i++) {
                item = {};
                item["name"] = print[0].totalAgeGroupSex[i].ageGroup;
                item["y"] = print[0].totalAgeGroupSex[i].female;
                item["drilldown"] = print[0].totalAgeGroupSex[i].ageGroup + 'f';
                femalePerAgeGroup.push(item);
            }


            var male = [];
            for (i = 0; i < print[0].totalAgeGroupSex.length; i++) {
                item = {};
                item["name"] = 'Male';
                item["id"] = print[0].totalAgeGroupSex[i].ageGroup + 'm';
                item["type"] = 'column';
                data = [];
                for (x = 0; x < print[0].ageGroupBarangay.length; x++) {
                    if (print[0].totalAgeGroupSex[i].ageGroup == print[0].ageGroupBarangay[x].ageGroup) {
                        item2 = {}
                        item2["name"] = print[0].ageGroupBarangay[x].location;
                        item2["y"] = print[0].ageGroupBarangay[x].male;
                        data.push(item2);
                    }
                }
                item["data"] = data;
                male.push(item);
            }
            var drilldownCategories = [];
            for (i = 0; i < print[0].totalAgeGroupSex.length; i++) {
                item = {};
                item["name"] = 'Female';
                item["id"] = print[0].totalAgeGroupSex[i].ageGroup + 'f';
                item["type"] = 'column';
                data = [];
                for (x = 0; x < print[0].ageGroupBarangay.length; x++) {
                    if (print[0].totalAgeGroupSex[i].ageGroup == print[0].ageGroupBarangay[x].ageGroup) {
                        item2 = {}
                        item2["name"] = print[0].ageGroupBarangay[x].location;
                        item2["y"] = print[0].ageGroupBarangay[x].female;
                        data.push(item2);
                        drilldownCategories.push(print[0].ageGroupBarangay[x].location);
                    }
                }
                item["data"] = data;
                male.push(item);
            }
            var topCategories = [];
            for (var i = 0; i < print[0].totalAgeGroupSex.length; i++) {
                topCategories.push(print[0].totalAgeGroupSex[i].ageGroup);
            }
            
            // Create the chart
            $('#output').highcharts({
                chart: {
                    type: 'bar',
                    zoomType: 'x',
                    panning: true,
                    panKey: 'shift',
                    events: {
                        drilldown: function (e) {
                            var chart = this;
                            Highcharts.charts[0].xAxis[0].update({categories: drilldownCategories,
                                reversed: true,
                                labels: {
                                    step: 1
                                }});
                        },
                        drillup: function (e) {
                            var chart = this;
                            chart.xAxis[0].update({categories: topCategories,
                                reversed: false,
                                labels: {
                                    step: 1
                                }
                            });
                        }
                    }
                },
                title: {
                    text: 'Household Population by Age Group and Sex for ' + print[0].Total.latestYear
                },
                subtitle: {
                    text: 'Click and drag to zoom in. Hold down shift key to pan.'
                },
                plotOptions: {
                    series: {
                        dataLabels: {
                            stacking: 'normal'
                        }
                    }
                },
                xAxis: [{
                        categories: topCategories,
                        reversed: false,
                        labels: {
                            step: 1
                        }
                    }],
                yAxis: {
                    title: {
                        text: null
                    },
                    labels: {
                        formatter: function () {
                            return Math.abs(this.value);
                        }
                    }
                },
                plotOptions: {
                    series: {
                        stacking: 'normal'
                    }
                },
                tooltip: {
                    formatter: function () {
                        return '<b>' + this.series.name + ', age ' + this.point.category + '</b><br/>' +
                                'Population: ' + Highcharts.numberFormat(Math.abs(this.point.y), 0);
                    }
                },
                series: [{
                        name: 'Male',
                        data: malePerAgeGroup
                    }, {
                        name: 'Female',
                        color: '#FF9999',
                        data: femalePerAgeGroup
                    }],
                drilldown: {
                series: male
                }
            })
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}

    var print;
    var city = "Caloocan City";
    function setMaritalStatus(chart) {
        $.ajax({
        url: "SetMaritalStatusServlet",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {
            var print = data;
            for (var i = 0; i < print[0].years.length; i++) {
                    $('#years').append('<input type="checkbox" class="filter" id="year" value="' 
                            + print[0].years[i].year + '"checked>'
                            +print[0].years[i].year+'</input></br>');
                }

                //district
                for (var i = 0; i < print[0].districts.length; i++) {
                    if(print[0].districts[i].district!=city){
                        $('#districts').append('<input type="checkbox" class="filter" id="district" value="' 
                                + print[0].districts[i].district + '" checked>'+print[0].districts[i].district+'</input></br>');
                    }
                }
                if(chart=="0"||chart=="Pie Chart"){
                    drawMaritalStatusBar(print, 2015,'pie');
                }
                else if(chart=="0"||chart=="Bar Chart"){
                    drawMaritalStatusBar(print, 2015,'column');
                }
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
    }
    
    var year = [];
    var district = [];
    var barangay = [];
    var analysischart = [];

    
    //CLICK location
    $(document).on("click", '.filter', function () {
        year = [];
        district = [];
        analysischart[0] = print[0];

        $('[id="district"]:checked').each(function (e) {
            var id = $(this).attr('value');
            district.push(id);
        });

        $('[id="year"]:checked').each(function (e) {
            var id = $(this).attr('value');
            year.push(id);    
        });
        
        removeYear(analysischart, year);
        removeDistrict(analysischart, district);
        chart(analysischart);

    });

    function removeYear(analysischart, year){
        analysischart[0].years.length = 0;
        for (var x = 0; x < year.length; x++) {
            item = {};
            item["year"] = year[x];
            analysischart[0].years.push(item);
        }
    }
    
    function removeDistrict(analysischart, district){
        analysischart[0].districts.length = 0;

        for (var x = 0; x < district.length; x++) {
            item = {};
            item['district'] = district[x];
            analysischart[0].districts.push(item);
        }
    }
    
    function removeBarangay(analysischart, barangay){
        analysischart[0].barangays.length = 0;

        for (var x = 0; x < barangay.length; x++) {
            item = {};
            item["barangay"] = barangay[x];
            analysischart[0].barangays.push(item);
        }
    }
    
    
    function drawMaritalStatusBar(print, year, chart){
            var total = [];
            widowedItem = {};
            var widowedTotal = 0;
            widowedItem["name"] = 'Widowed';
            widowedItem["drilldown"] = 'widowed';
            for (var i = 0; i < print[0].people.length; i++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].people[i].district == print[0].districts[y].district){
                            if(print[0].people[i].year == year){
                                widowedTotal+=print[0].people[i].widowed;
                            }
                        }
                    }
            }
            widowedItem["y"] = widowedTotal;
            total.push(widowedItem);
            
            unknownItem = {};
            var unknownTotal = 0;
            unknownItem["name"] = 'Unknown';
            unknownItem["drilldown"] = 'unknown';
            for (var i = 0; i < print[0].people.length; i++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].people[i].district == print[0].districts[y].district){
                            if(print[0].people[i].year == year){
                                unknownTotal+=print[0].people[i].unknown;
                            }
                        }
                    }
            }
            unknownItem["y"] = unknownTotal;
            total.push(unknownItem);
            
            marriedItem = {};
            var marriedTotal = 0;
            marriedItem["name"] = 'Married';
            marriedItem["drilldown"] = 'married';
            for (var i = 0; i < print[0].people.length; i++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].people[i].district == print[0].districts[y].district){
                            if(print[0].people[i].year == year){
                                marriedTotal+=print[0].people[i].married;
                            }
                        }
                    }
            }
            marriedItem["y"] = marriedTotal;
            total.push(marriedItem);
            
            divorcedItem = {};
            var divorcedTotal = 0;
            divorcedItem["name"] = 'Divorced';
            divorcedItem["drilldown"] = 'divorced';
            for (var i = 0; i < print[0].people.length; i++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].people[i].district == print[0].districts[y].district){
                            if(print[0].people[i].year == year){
                                divorcedTotal+=print[0].people[i].divorced;
                            }
                        }
                    }
            }
            divorcedItem["y"] = divorcedTotal;
            total.push(divorcedItem);
            
            liveInItem = {};
            var liveInTotal = 0;
            liveInItem["name"] = 'Live-In';
            liveInItem["drilldown"] = 'liveIn';
            for (var i = 0; i < print[0].people.length; i++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].people[i].district == print[0].districts[y].district){
                            if(print[0].people[i].year == year){
                                liveInTotal+=print[0].people[i].liveIn;
                            }
                        }
                    }
            }
            liveInItem["y"] = liveInTotal;
            total.push(liveInItem);
            
            singleItem = {};
            var singleTotal = 0;
            singleItem["name"] = 'Single';
            singleItem["drilldown"] = 'single';
            for (var i = 0; i < print[0].people.length; i++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].people[i].district == print[0].districts[y].district){
                            if(print[0].people[i].year == year){
                                singleTotal+=print[0].people[i].single;
                            }
                        }
                    }
            }
            singleItem["y"] = singleTotal;
            total.push(singleItem);
            
            var drilldowns = [];
            var south = 0; 
            var north = 0;
            item={};
            item["name"] = 'Widowed';
            item["id"] = 'widowed';
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].districts.length; y++){
                    if(print[0].people[i].district == print[0].districts[y].district){
                        if(print[0].people[i].year == year){
                            if(print[0].people[i].zone==='NORTH'){
                                north+=print[0].people[i].widowed;
                            }
                            else if(print[0].people[i].zone==='SOUTH'){
                                south+=print[0].people[i].widowed;
                            }
                        }
                    }
                }
            }
            data = [];
            item2 = {};
            item2["name"] = 'North';
            item2["y"] = north;
            item2["drilldown"] = 'widowedNorth';
            data.push(item2);
            item2 = {};
            item2["name"] = 'South';
            item2["y"] = south;
            item2["drilldown"] = 'widowedSouth';
            data.push(item2);
            item['data'] = data;
            drilldowns.push(item);
            
            var south = 0; 
            var north = 0;
            item={};
            item["name"] = 'Married';
            item["id"] = 'married';
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].districts.length; y++){
                    if(print[0].people[i].district == print[0].districts[y].district){
                        if(print[0].people[i].year == year){
                            if(print[0].people[i].zone==='NORTH'){
                                north+=print[0].people[i].married;
                            }
                            else if(print[0].people[i].zone==='SOUTH'){
                                south+=print[0].people[i].married;
                            }
                        }
                    }
                }
            }
            data = [];
            item2 = {};
            item2["name"] = 'North';
            item2["y"] = north;
            item2["drilldown"] = 'marriedNorth';
            data.push(item2);
            item2 = {};
            item2["name"] = 'South';
            item2["y"] = south;
            item2["drilldown"] = 'marriedSouth';
            data.push(item2);
            item['data'] = data;
            drilldowns.push(item);
            
            var south = 0; 
            var north = 0;
            item={};
            item["name"] = 'Unknown';
            item["id"] = 'unknown';
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].districts.length; y++){
                    if(print[0].people[i].district == print[0].districts[y].district){
                        if(print[0].people[i].year == year){
                            if(print[0].people[i].zone==='NORTH'){
                                north+=print[0].people[i].unknown;
                            }
                            else if(print[0].people[i].zone==='SOUTH'){
                                south+=print[0].people[i].unknown;
                            }
                        }
                    }
                }
            }
            data = [];
            item2 = {};
            item2["name"] = 'North';
            item2["y"] = north;
            item2["drilldown"] = 'unknownNorth';
            data.push(item2);
            item2 = {};
            item2["name"] = 'South';
            item2["y"] = south;
            item2["drilldown"] = 'unknownSouth';
            data.push(item2);
            item['data'] = data;
            drilldowns.push(item);
            
            var south = 0; 
            var north = 0;
            item={};
            item["name"] = 'Divorced';
            item["id"] = 'divorced';
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].districts.length; y++){
                    if(print[0].people[i].district == print[0].districts[y].district){
                        if(print[0].people[i].year == year){
                            if(print[0].people[i].zone==='NORTH'){
                                north+=print[0].people[i].divorced;
                            }
                            else if(print[0].people[i].zone==='SOUTH'){
                                south+=print[0].people[i].divorced;
                            }
                        }
                    }
                }
            }
            data = [];
            item2 = {};
            item2["name"] = 'North';
            item2["y"] = north;
            item2["drilldown"] = 'divorcedNorth';
            data.push(item2);
            item2 = {};
            item2["name"] = 'South';
            item2["y"] = south;
            item2["drilldown"] = 'divorcedSouth';
            data.push(item2);
            item['data'] = data;
            drilldowns.push(item);
            
            var south = 0; 
            var north = 0;
            item={};
            item["name"] = 'Live-In';
            item["id"] = 'liveIn';
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].districts.length; y++){
                    if(print[0].people[i].district == print[0].districts[y].district){
                        if(print[0].people[i].year == year){
                            if(print[0].people[i].zone==='NORTH'){
                                north+=print[0].people[i].liveIn;
                            }
                            else if(print[0].people[i].zone==='SOUTH'){
                                south+=print[0].people[i].liveIn;
                            }
                        }
                    }
                }
            }
            data = [];
            item2 = {};
            item2["name"] = 'North';
            item2["y"] = north;
            item2["drilldown"] = 'liveInNorth';
            data.push(item2);
            item2 = {};
            item2["name"] = 'South';
            item2["y"] = south;
            item2["drilldown"] = 'liveInSouth';
            data.push(item2);
            item['data'] = data;
            drilldowns.push(item);
            
            var south = 0; 
            var north = 0;
            item={};
            item["name"] = 'Single';
            item["id"] = 'single';
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].districts.length; y++){
                    if(print[0].people[i].district == print[0].districts[y].district){
                        if(print[0].people[i].year == year){
                            if(print[0].people[i].zone==='NORTH'){
                                north+=print[0].people[i].single;
                            }
                            else if(print[0].people[i].zone==='SOUTH'){
                                south+=print[0].people[i].single;
                            }
                        }
                    }
                }
            }
            data = [];
            item2 = {};
            item2["name"] = 'North';
            item2["y"] = north;
            item2["drilldown"] = 'singleNorth';
            data.push(item2);
            item2 = {};
            item2["name"] = 'South';
            item2["y"] = south;
            item2["drilldown"] = 'singleSouth';
            data.push(item2);
            item['data'] = data;
            drilldowns.push(item);


            item={};
            item["id"] = 'singleNorth';
            item["name"] = 'Single in North Caloocan';
            data = [];
            for (var i = 0; i < print[0].people.length; i++) {
                
                for(var y = 0; y < print[0].districts.length; y++){
                    if(print[0].people[i].district == print[0].districts[y].district){
                        if(print[0].people[i].year == year){
                            if(print[0].people[i].zone==='NORTH'){
                                alert(print[0].people[i].single);
                                item2 = {};
                                item2["name"] =  print[0].people[i].district;
                                item2["y"] = print[0].people[i].single;
                                data.push(item2);
                            }
                        }
                    }
                }
            }
            item['data'] = data;
            drilldowns.push(item);
            console.log(JSON.stringify(drilldowns));
            
            $('#output').highcharts({
                chart: {
                    type: chart,
                    drilled: false,
                    zoomType: 'xy',
                    panning: true,
                    panKey: 'shift'//,
                },
                title: {
                    text: 'Household Population 10 Yrs Old and Over by Age Group, Sex, and Marital Status for ' + year
                },
                xAxis: {
                    type: 'category'
                },
                series: [{
                    name: 'Caloocan City',
                    data: total
                }],
                drilldown: {
                    series: drilldowns
                    }
            });
        }



function setClassroomRequirement(){
    $.ajax({
        url: "SetClassroomRequirementServlet",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {
            var print = data;   
            var totalEnrollees = [];
            for (var i = 0; i < print[0].years.length; i++) {
                var totals = 0;
                item = {};
                item["name"] = print[0].years[i].year;
                item["drilldown"] = print[0].years[i].year + 'e';
                item["type"] = 'column';
                data = [];
                for (var x = 0; x < print[0].enrollment.length; x++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].enrollment[x].district == print[0].districts[y].district){
                            if (print[0].years[i].year == print[0].enrollment[x].year) {
                                totals = totals + parseInt(print[0].enrollment[x].enrollment); 
                            }
                        }
                    }
                }
                item["y"] = totals;
                totalEnrollees.push(item);
            }
            
            var totalClassrooms = [];
            for (var i = 0; i < print[0].years.length; i++) {
                var totals = 0;
                item = {};
                item["name"] = print[0].years[i].year;
                item["drilldown"] = print[0].years[i].year + 'c';
                item["type"] = 'column';
                data = [];
                for (var x = 0; x < print[0].classrooms.length; x++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].classrooms[x].district == print[0].districts[y].district){
                            if (print[0].years[i].year == print[0].classrooms[x].year) {
                                totals = totals + (parseInt(print[0].classrooms[x].classrooms)*45); 
                            }
                        }
                    }
                }
                item["y"] = totals;
                totalClassrooms.push(item);
            }
            
            var totalGaps = [];
            for (var i = 0; i < print[0].years.length; i++) {
                var totals = 0;
                item = {};
                item["name"] = print[0].years[i].year;
                item["drilldown"] = print[0].years[i].year + 'g';
                item["type"] = 'column';
                data = [];
                for (var x = 0; x < print[0].gaps.length; x++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].gaps[x].district == print[0].districts[y].district){
                            if (print[0].years[i].year == print[0].gaps[x].year) {
                                totals = totals + parseInt(print[0].gaps[x].gap); 
                            }
                        }
                    }
                }
                item["y"] = totals;
                totalGaps.push(item);
            }
            
            $('#output').highcharts({
            chart: {
                type: 'column',
                drilled: false,
                zoomType: 'xy',
                panning: true,
                panKey: 'shift'
            },
            title: {
                text: 'Classroom Requirements in Public Elementary School'
            },
            xAxis: {
                type: 'category'
            },
            plotOptions: {
                column: {
//        stacking: 'normal'
                    dataLabels: {
                        enabled: true
                    },
                    series: {
                        allowPointSelect: true
                    }
                },
            },
            series: [{
                    name: 'Enrollees',
                    type: 'column',
                    data: totalEnrollees
                },  {
                    name: 'Classrooms',
                    type: 'column',
                    data: totalClassrooms
                }, {
                    name: 'Gaps',
                    type: 'column',
                    color: '#FF3232',
                    data: totalGaps
                    
                }
            ],
            drilldown: {
                //series: drilldownsHospital
            }
        });
            
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}

function setEnrollmentTeacherClassroom(){
    $.ajax({
        url: "EnrollmentTeachersClassrooms",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {
            var print = data;   
            var totalEnrollees = [];
            for (var i = 0; i < print[0].years.length; i++) {
                var totals = 0;
                item = {};
                item["name"] = print[0].years[i].year;
                item["drilldown"] = print[0].years[i].year + 'e';
                item["type"] = 'column';
                data = [];
                for (var x = 0; x < print[0].enrollment.length; x++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].enrollment[x].district == print[0].districts[y].district){
                            if (print[0].years[i].year == print[0].enrollment[x].year) {
                                totals = totals + parseInt(print[0].enrollment[x].enrollment); 
                            }
                        }
                    }
                }
                item["y"] = totals;
                totalEnrollees.push(item);
            }
            
            var totalClassrooms = [];
            for (var i = 0; i < print[0].years.length; i++) {
                var totals = 0;
                item = {};
                item["name"] = print[0].years[i].year;
                item["drilldown"] = print[0].years[i].year + 'c';
                item["type"] = 'column';
                data = [];
                for (var x = 0; x < print[0].classrooms.length; x++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].classrooms[x].district == print[0].districts[y].district){
                            if (print[0].years[i].year == print[0].classrooms[x].year) {
                                totals = totals + (parseInt(print[0].classrooms[x].classrooms)); 
                            }
                        }
                    }
                }
                item["y"] = totals;
                totalClassrooms.push(item);
            }
            
            var totalTeachers = [];
            for (var i = 0; i < print[0].years.length; i++) {
                var totals = 0;
                item = {};
                item["name"] = print[0].years[i].year;
                item["drilldown"] = print[0].years[i].year + 't';
                item["type"] = 'column';
                data = [];
                for (var x = 0; x < print[0].classrooms.length; x++) {
                    for(var y = 0; y < print[0].districts.length; y++){
                        if(print[0].classrooms[x].district == print[0].districts[y].district){
                            if (print[0].years[i].year == print[0].classrooms[x].year) {
                                totals = totals + (parseInt(print[0].classrooms[x].teachers)); 
                            }
                        }
                    }
                }
                item["y"] = totals;
                totalTeachers.push(item);
            }
            
            
            $('#output').highcharts({
            chart: {
                type: 'column',
                drilled: false,
                zoomType: 'xy',
                panning: true,
                panKey: 'shift'
            },
            title: {
                text: 'Classroom Requirements in Public Elementary School'
            },
            xAxis: {
                type: 'category'
            },
            plotOptions: {
                column: {
                    dataLabels: {
                        enabled: true
                    },
                    series: {
                        allowPointSelect: true
                    }
                },
            },
            series: [{
                    name: 'Enrollees',
                    type: 'column',
                    data: totalEnrollees
                },  {
                    name: 'Teachers',
                    type: 'column',
                    data: totalTeachers
                }, {
                    name: 'Classrooms',
                    type: 'column',
                    color: '#FF3232',
                    data: totalClassrooms
                    
                }
            ],
            drilldown: {
                //series: drilldownsHospital
            }
        });
            
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}