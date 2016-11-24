/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors: Shermaine Sy, Gian Carlo Roxas, Geraldine Atayan
 */


function autoCompleteSchool() {
    $("#schoolNameSearch").devbridgeAutocomplete({
        serviceUrl: 'SearchSchoolNamePublic',
        type: 'POST',
        showNoSuggestionNotice: true,
        noSuggestionNotice: 'No existing school'
    });
}

function setSchoolData() {
    var schoolName = document.getElementById('schoolNameSearch').value;

    var classification = "public";
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
//            alert(data.length);
            $('#SchoolDirectory').hide();
            $('#viewAll').removeAttr("disabled");
            $('#dataSchool').remove();
            var str = '<table class="table table-bordered" id="dataSchool"><tbody id="data"></tbody></table>';
            document.getElementById("dataHolder").innerHTML = str;
            $('#data').append('<tr style = "background-color: #454545; color: #fff" >\n\
                                        <th colspan="2" style="vertical-align: bottom; text-align: left;" >Name of School</th>\n\
                                        <td class="nr" colspan = "4" style="border-right: none; text-align: left;">' + schoolData[0].school[0].schoolName + '</td> \n\
                                         <th colspan="1" style="vertical-align: bottom; text-align: left;" >School ID</th> \n\
                                        <td class="si" colspan = "2" style="vertical-align: bottom; border-right: none; text-align: left;"> ' + schoolData[0].school[0].schoolID + ' </td>  \n\
                                        <td style="border-left: none; text-align: right"> \n\
                                            <button id="updateDirectory" class="upadateBtn btn btn-success btn-sm"><span class="fa fa-edit"></span> Edit</button> \n\
                                            <button id="invalidDirectory"  class="deleteBtn btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span> Remove</button> \n\
                                        </td> \n\
             </tr>');

            $('#data').append('<tr>\n\
                               <th colspan="6">Teachers</th>\n\
                               <th colspan="2">Classrooms</th>\n\
                               <th colspan="2">Seats</th>\n\
                               </tr><tr>\n\
                    <th colspan="3">Elementary</th>\n\
                    <th colspan="3">Kinder</th>\n\
                    <th rowspan="2" style="vertical-align: bottom; \n\
                                            border-left: solid; \n\
                                            border-width: thin;\n\
                                            border-color: #d2d6de;\n\
                                            padding: 1%;">Kinder</th>\n\
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
                </tr><tr id="this" >');


//          var x = document.createElement("tr");
//          
//           $('#data').append(x);
            var totalKinder;
            var totalElem;
            for (var i = 0; i < schoolData[0].teacher.length; i++) {
                if (schoolData[0].teacher[i].gradeLevel == 'Kinder') {
                    totalKinder = schoolData[0].teacher[i].maleCount + schoolData[0].teacher[i].femaleCount;
                    $('#this').append('<td class="KtMale search-border">' + schoolData[0].teacher[i].maleCount + '</td>\n\
                    <td class="KtFemale search-border">' + schoolData[0].teacher[i].femaleCount + '</td>\n\
\                   <td class="KTotal search-border">' + totalKinder + '</td>');
                }
                if (schoolData[0].teacher[i].gradeLevel == 'Elementary') {
                    totalElem = schoolData[0].teacher[i].maleCount + schoolData[0].teacher[i].femaleCount;
                    $('#this').append('<td class="EtMale search-border">' + schoolData[0].teacher[i].maleCount + '</td>\n\
                    <td class="EtFemale search-border">' + schoolData[0].teacher[i].femaleCount + '</td>\n\
                    <td class="ETotal search-border">' + totalElem + '</td>');
                }
            }

            var classK;
            var totalClassroom = 0;
            for (var i = 0; i < schoolData[0].classrooms.length; i++) {
                if (schoolData[0].classrooms[i].ClassgradeLevel == 'Kinder') {
                    classK = schoolData[0].classrooms[i].roomCount;
                }
                if (schoolData[0].classrooms[i].ClassgradeLevel == 'Elementary') {
                    $('#this').append('<td class="EClassroom search-border">' + schoolData[0].classrooms[i].roomCount + '</td>');
                }
                totalClassroom = totalClassroom + schoolData[0].classrooms[i].roomCount;
            }
            $('#this').append('<td class="KClassroom search-border">' + classK + '</td>');

            var totalSeats = 0;
            for (var i = 0; i < schoolData[0].seats.length; i++) {
                if (schoolData[0].seats[i].SeatsgradeLevel == 'Kinder') {
                    $('#this').append('<td class="Kseats search-border">' + schoolData[0].seats[i].seatCount + '</td>');
                }
                if (schoolData[0].seats[i].SeatsgradeLevel == 'Elementary') {
                    $('#this').append('<td class="Eseats search-border">' + schoolData[0].seats[i].seatCount + '</td>');
                }
                totalSeats = totalSeats + schoolData[0].seats[i].seatCount;
            }

            $('#data').append('</tr><tr>\n\
                                         <th  class="censusYear" colspan="5"><input type="hidden" id="censusYear" value="' + schoolData[0].teacher[0].censusYear + '"/>Total Teachers</th>\n\
                                        <td class="totalAllteachers">' + (totalKinder + totalElem) + '</td>\n\
                                        <th>Total Classrooms</th>\n\
                                        <td class="totalAllClassroom">' + totalClassroom + '</td>\n\
                                        <th>Total Seats</th>\n\
                                        <td class="totalAllSeats">' + totalSeats + '</td>\n\
                                    </tr>');

            $('input:text').focus(
                    function () {
                        $('#schoolNameSearch').val('');
                    });



            var page = document.getElementById('page').value;
            var classification = document.getElementById('classificationChecker').value;

            $.ajax({
                url: "UploadCheckerDirectory",
                type: 'POST',
                dataType: "JSON",
                data: {
                    page: page,
                    classification: classification
                }, success: function (data) {
                    console.log(data);
                    if (data === "approved") {
                  
                        $('.upadateBtn').prop('disabled', true);
                        $('.deleteBtn').prop('disabled', true);
                        $('#btnsubmit').prop('disabled', true);
                        $('#addnew').prop('disabled', true);

                    }
                }, error: function (XMLHttpRequest, textStatus, exception) {
                    console.log(exception);
                }
            });
        }, error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
}