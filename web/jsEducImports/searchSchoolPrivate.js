/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function autoCompleteSchool() {
    $("#schoolNameSearch").devbridgeAutocomplete({
        serviceUrl: 'SearchSchoolNamePrivate',
        type: 'POST',
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'No existing School'
    });
}

function setSchoolData() {
     var schoolName = document.getElementById('schoolNameSearch').value;
    var classification = "private";
    $.ajax({
        url: "SetSchoolData",
        type: 'POST',
        dataType: "JSON",
        data: {
            schoolName: schoolName,
            classification: classification
        },
        success: function (data) {
            var schoolData = data;
    
            $('#SchoolDirectory').hide();
            $('#viewAll').removeAttr("disabled");
            $('#dataSchool').remove();
            var str = '<table class="table table-bordered" id="dataSchool"><tbody id="data"></tbody></table>';
            document.getElementById("dataHolder").innerHTML = str;
            $('#data').append('<tr style = "background-color: #454545; color: #fff" >\n\
                                        <th style="vertical-align: bottom; text-align: left;" >Name of School</th>\n\
                                        <td class="nr" colspan = "8" style="border-right: none; text-align: left;">' + schoolData[0].school[0].schoolName + '</td> \n\
                                        <td style="border-left: none; text-align: right"> \n\
                                            <button id="updateDirectory" class="btn btn-success btn-sm"><span class="fa fa-edit"></span> Edit</button> \n\
                                            <button id="invalidDirectory"  class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span> Remove</button> \n\
                                        </td> \n\
             </tr>');

            $('#data').append('<tr>\n\
                               <th colspan="6">Teachers</th>\n\
                               <th colspan="2">Classrooms</th>\n\
                               <th colspan="2">Seats</th>\n\
                               </tr><tr>\n\
                    <th colspan="3">Kinder</th>\n\
                    <th colspan="3">Elementary</th>\n\
                    <th rowspan="2" style="vertical-align: bottom; \n\
                                            border-left: solid; \n\
                                            border-width: thin;\n\
                                            border-color: #d2d6de;\n\
                                            padding: 1%;"">Kinder</th>\n\
                    <th rowspan="2" style="vertical-align: bottom; ">Elementary</th>\n\
                    <th rowspan="2" style="vertical-align: bottom; ">Kinder</th>\n\
                    <th rowspan="2" style="vertical-align: bottom; ">Elementary</th>\n\
                </tr>\n\
                <tr>\n\
                    <th>Male</th>\n\
                    <th>Female</th>\n\
                    <th>Total</th>\n\
                    <th>Male</th>\n\
                    <th>Female</th>\n\
                    <th>Total</th>\n\
                </tr>');

            $('#data').append('<tr>');
            var totalKinder;
            var totalElem;
            for (var i = 0; i < schoolData[0].teacher.length; i++) {
                if (schoolData[0].teacher[i].gradeLevel == 'Kinder') {
                    totalKinder = schoolData[0].teacher[i].maleCount + schoolData[0].teacher[i].femaleCount;
                    $('#data').append('<td class="search-border">' + schoolData[0].teacher[i].maleCount + '</td>\n\
                    <td class="search-border">' + schoolData[0].teacher[i].femaleCount + '</td>\n\
\                   <td class="search-border">' + totalKinder + '</td>');
                }
                if (schoolData[0].teacher[i].gradeLevel == 'Elementary') {
                    totalElem = schoolData[0].teacher[i].maleCount + schoolData[0].teacher[i].femaleCount;
                    $('#data').append('<td class="search-border">' + schoolData[0].teacher[i].maleCount + '</td>\n\
                    <td class="search-border">' + schoolData[0].teacher[i].femaleCount + '</td>\n\
                    <td class="search-border">' + totalElem + '</td>');
                }
            }

            var classK;
            var totalClassroom = 0;
            for (var i = 0; i < schoolData[0].classrooms.length; i++) {
                if (schoolData[0].classrooms[i].ClassgradeLevel == 'Kinder') {
                    classK = schoolData[0].classrooms[i].roomCount;
                }
                if (schoolData[0].classrooms[i].ClassgradeLevel == 'Elementary') {
                    $('#data').append('<td class="search-border">' + schoolData[0].classrooms[i].roomCount + '</td>');
                }
                totalClassroom = totalClassroom + schoolData[0].classrooms[i].roomCount;
            }
            $('#data').append('<td class="search-border">' + classK + '</td>');

            var totalSeats = 0;
            for (var i = 0; i < schoolData[0].seats.length; i++) {
                if (schoolData[0].seats[i].SeatsgradeLevel == 'Kinder') {
                    $('#data').append('<td class="search-border">' + schoolData[0].seats[i].seatCount + '</td>');
                }
                if (schoolData[0].seats[i].SeatsgradeLevel == 'Elementary') {
                    $('#data').append('<td class="search-border">' + schoolData[0].seats[i].seatCount + '</td>');
                }
                totalSeats = totalSeats + schoolData[0].seats[i].seatCount;
            }
            $('#data').append('</tr>');

            $('#data').append('<tr>\n\
                                        <th colspan="5">Total Teachers</th>\n\
                                        <td>' + (totalKinder + totalElem) + '</td>\n\
                                        <th>Total Classrooms</th>\n\
                                        <td>' + totalClassroom + '</td>\n\
                                        <th>Total Seats</th>\n\
                                        <td>' + totalSeats + '</td>\n\
                                    </tr>');
                
            $('input:text').focus(
                    function () {
                        $('#schoolNameSearch').val('');
                    });
                    
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}