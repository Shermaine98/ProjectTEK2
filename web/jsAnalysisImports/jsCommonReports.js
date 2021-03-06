/* 
 *  ProjectTEK - DLSU CCS 2016
 *  Authors:  Gian Carlo Roxas, Shermaine Sy, Geraldine Atayan
 */


var print;
    var city = "Caloocan City";
    var chartSelected;
    var reportSelected; 
    var year = [];
    var district = [];
    var barangay = [];
    var gender = [];
    var analysischart = []; 
    var gradeLevel = [];

    function filterYear(){
        var year = $('#years').find(":selected").text();
        if(reportSelected == 'maritalStatus'){
            if(chartSelected=="0"||chartSelected=="Pie Chart"){
                drawMaritalStatusBar(print, year,'pie');
            }
            else if(chartSelected=="0"||chartSelected=="Bar Chart"){
                drawMaritalStatusBar(print, year,'column');
            }
            else if(chartSelected=="0"||chartSelected=="Table"){
                drawMaritalStatusTable(print, year);
            }
        }
        
        if(reportSelected == 'ageGroup'){
            if(chartSelected=="0"||chartSelected=="Population Pyramid"){
                drawHHPopPyramid(print, year);
            }
            else if(chartSelected=="0"||chartSelected=="Pie Chart"){
                drawHHPopAgeGroupSexPie(print, year, 'pie');
            }
            else if(chartSelected=="0"||chartSelected=="Bar Chart"){
                drawHHPopAgeGroupSexPie(print, year, 'column');
            }
            else if(chartSelected=="0"||chartSelected=="Table"){
                drawHHPopTable(print, year);
            }
        }
        
        if(reportSelected == 'nutritionalStatus'){
            if(chartSelected=="0"||chartSelected=="Pie Chart"){
                drawNutritionalStatus(print, year, 'pie');
            }
            else if(chartSelected=="0"||chartSelected=="Bar Chart"){
                drawNutritionalStatus(print, year, 'column');
            }
        }
    }
    
    function format() {
        $(".number").each(function () {
            var x = $(this).text();
            $(this).text(x.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ","));
        });
    }
    
    $(document).on("click", '.filter', function () {
        gender = [];
        barangay = [];
        analysischart[0] = print[0];
        gradeLevel = [];
        yearsCheckboxes = [];
        genderCheckboxes = [];
        classificationCheckboxes = [];
        gradeLevelCheckboxes = [];

        $('[id="barangayss"]:checked').each(function (e) {
            var id = $(this).attr('value');
            barangay.push(id);
        });
        
        $('[id="genders"]:checked').each(function (e) {
            var id = $(this).attr('value');
            gender.push(id);
        });
        
//        $('[id="gradeLevels"]:checked').each(function (e) {
//            var id = $(this).attr('value');
//            gradeLevel.push(id);
//        });
        
        removeGender(analysischart, gender);
        removeBarangay(analysischart, barangay);
        removeGradeLevel(analysischart, gradeLevel);
        
        if(reportSelected == 'nutritionalStatus'){
            $('[id="yearCheckboxes"]:checked').each(function (e) {
                var id = $(this).attr('value');
                yearsCheckboxes.push(id);
            });

//            $('[id="genders"]:checked').each(function (e) {
//                var id = $(this).attr('value');
//                gender.push(id);
//            });

            $('[id="gradeLevels"]:checked').each(function (e) {
                var id = $(this).attr('value');
                gradeLevel.push(id);
            });
            removeYearCheckboxes(analysischart, yearsCheckboxes);
            //removeGenderCheckboxes(analysischart, gender);
            removeGradeLevelCheckboxes(analysischart, gradeLevel);
            
            if(chartSelected=="Line Chart"){
                drawNutritionalStatus(analysischart, 'spline', false);
            }
            else if(chartSelected=="Bar Chart"){
                drawNutritionalStatus(analysischart, 'column',false);
            }
            else if(chartSelected=="Stacked Bar Chart"){
                drawNutritionalStatus(analysischart, 'column',true);
            }
            else if(chartSelected=="Table"){
                drawNutritionalStatusTable(analysischart);
            }
        }
        
        if(reportSelected == 'kinderEnrollment'){
            $('[id="yearCheckboxes"]:checked').each(function (e) {
                var id = $(this).attr('value');
                yearsCheckboxes.push(id);
            });
            //alert(JSON.stringify(yearsCheckboxes));

            $('[id="genderCheckboxes"]:checked').each(function (e) {
                var id = $(this).attr('value');
                genderCheckboxes.push(id);
            });

            $('[id="classificationCheckboxes"]:checked').each(function (e) {
                var id = $(this).attr('value');
                classificationCheckboxes.push(id);
            });
            removeYearCheckboxes(analysischart, yearsCheckboxes);
            removeGenderCheckboxes(analysischart, genderCheckboxes);
            removeClassificationCheckboxes(analysischart, classificationCheckboxes);
            
            if(chartSelected=="0"||chartSelected=="Line Chart"){
                drawKinderEnrollment(analysischart, 'spline', false);
            }
            else if(chartSelected=="0"||chartSelected=="Bar Chart"){
                drawKinderEnrollment(analysischart, 'column', false);
            }
            else if(chartSelected=="0"||chartSelected=="Stacked Bar Chart"){
                drawKinderEnrollment(analysischart, 'column', true);
            }
            else if(chartSelected=="0"||chartSelected=="Table"){
                drawKinderEnrollmentTable(analysischart);
            }
        }
        
        if(reportSelected == 'elementaryEnrollment'){
            $('[id="yearCheckboxes"]:checked').each(function (e) {
                var id = $(this).attr('value');
                yearsCheckboxes.push(id);
            });
            //alert(JSON.stringify(yearsCheckboxes));

            $('[id="genderCheckboxes"]:checked').each(function (e) {
                var id = $(this).attr('value');
                genderCheckboxes.push(id);
            });

            $('[id="classificationCheckboxes"]:checked').each(function (e) {
                var id = $(this).attr('value');
                classificationCheckboxes.push(id);
            });
            
            $('[id="gradeLevelCheckboxes"]:checked').each(function (e) {
                var id = $(this).attr('value');
                gradeLevelCheckboxes.push(id);
            });
            
            removeYearCheckboxes(analysischart, yearsCheckboxes);
            removeGenderCheckboxes(analysischart, genderCheckboxes);
            removeClassificationCheckboxes(analysischart, classificationCheckboxes);
            removeGradeLevelCheckboxes(analysischart, gradeLevelCheckboxes);
            
            if(chartSelected=="0"||chartSelected=="Line Chart"){
                drawElementaryEnrollment(analysischart, 'spline', false);
            }
            else if(chartSelected=="0"||chartSelected=="Bar Chart"){
                drawElementaryEnrollment(analysischart, 'column', false);
            }
            else if(chartSelected=="0"||chartSelected=="Stacked Bar Chart"){
                drawElementaryEnrollment(analysischart, 'column', true);
            }
            else if(chartSelected=="0"||chartSelected=="Table"){
                drawElementaryEnrollmentTable(analysischart);
            }
        }
        
        var year = $('#years').find(":selected").val();
        if(reportSelected == 'maritalStatus'){
            if(chartSelected=="0"||chartSelected=="Pie Chart"){
                drawMaritalStatusBar(analysischart, year,'pie');
            }
            else if(chartSelected=="0"||chartSelected=="Bar Chart"){
                drawMaritalStatusBar(analysischart, year,'column');
            }
            else if(chartSelected=="0"||chartSelected=="Table"){
                drawMaritalStatusTable(analysischart, year);
            }
        } 
        if(reportSelected == 'ageGroup'){
            if(chartSelected=="0"||chartSelected=="Population Pyramid"){
                drawHHPopPyramid(analysischart, year);
            }
            else if(chartSelected=="0"||chartSelected=="Pie Chart"){
                drawHHPopAgeGroupSexPie(analysischart, year, 'pie');
            }
            else if(chartSelected=="0"||chartSelected=="Bar Chart"){
                drawHHPopAgeGroupSexPie(analysischart, year, 'column');
            }
            else if(chartSelected=="0"||chartSelected=="Table"){
                drawHHPopTable(analysischart, year);
            }
        }
    });

    function removeGender(analysischart, gender){
        if(analysischart[0].genders!=null){
            analysischart[0].genders.length = 0;
            for (var x = 0; x < gender.length; x++) {
                item = {};
                item['gender'] = gender[x];
                analysischart[0].genders.push(item);
            }
        }
    }
    
    function removeGradeLevel(analysischart, gradeLevel){
        if(analysischart[0].gradeLevels!=null){
            analysischart[0].gradeLevels.length = 0;
            for (var x = 0; x < gradeLevel.length; x++) {
                item = {};
                item['gradeLevel'] = gradeLevel[x];
                analysischart[0].gradeLevels.push(item);
            }
        }
    }
    
    function removeDistrict(analysischart, district){
        if(analysischart[0].districts!=null){
        analysischart[0].districts.length = 0;
            for (var x = 0; x < district.length; x++) {
                item = {};
                item['district'] = district[x];
                analysischart[0].districts.push(item);
            }
        }
    }
    
    function removeBarangay(analysischart, barangay){
        if(analysischart[0].barangays!=null){
            analysischart[0].barangays.length = 0;
            for (var x = 0; x < barangay.length; x++) {
                item = {};
                item["barangay"] = barangay[x];
                analysischart[0].barangays.push(item);
            }
        }
    }
    
    function removeYearCheckboxes(analysischart, year){
        if(analysischart[0].years!=null){
            analysischart[0].years.length = 0;
            for (var x = 0; x < year.length; x++) {
                item = {};
                item["year"] = year[x];
                analysischart[0].years.push(item);
            }
        }
    }
    
    function removeGenderCheckboxes(analysischart, gender){
        if(analysischart[0].genders!=null){
            analysischart[0].genders.length = 0;
            for (var x = 0; x < gender.length; x++) {
                item = {};
                item["gender"] = gender[x];
                analysischart[0].genders.push(item);
            }
        }
    }
    
    function removeClassificationCheckboxes(analysischart, classification){
        if(analysischart[0].classifications!=null){
            analysischart[0].classifications.length = 0;
            for (var x = 0; x < classification.length; x++) {
                item = {};
                item["classification"] = classification[x];
                analysischart[0].classifications.push(item);
            }
        }
    }
    
    function removeGradeLevelCheckboxes(analysischart, gradeLevel){
        if(analysischart[0].gradeLevels!=null){
            analysischart[0].gradeLevels.length = 0;
            for (var x = 0; x < gradeLevel.length; x++) {
                item = {};
                item["gradeLevel"] = gradeLevel[x];
                analysischart[0].gradeLevels.push(item);
            }
        }
    }

function setElementaryEnrollments(chart){
    $.ajax({
        url: "ElementaryEnrollmentServlet",
        type: 'POST',
        dataType: "JSON",
        success: function(data){
            reportSelected = 'elementaryEnrollment';
            print = data;
            chartSelected = chart;
            $('#years').empty();
            $('#genderCheckbox').empty();
            $('#classificationCheckbox').empty();
            
            for (var i =0; i <  print[0].years.length; i++) {
                $('#yearsCheckbox').append('<input type="checkbox" class="filter" id="yearCheckboxes" value="' 
                        +print[0].years[i].year+ '" checked>'
                        +'&nbsp;'+print[0].years[i].year+'</input></br>');
            }
            //gender
            
            print[0].genders.length = 0;
            item = {};
            item["gender"] = 'Female';
            print[0].genders.push(item);
            item = {};
            item["gender"] = 'Male';
            print[0].genders.push(item);
            
            for (var i = 0; i < print[0].genders.length; i++) {
                    $('#genderCheckbox').append('<input type="checkbox" class="filter" id="genderCheckboxes" value="' 
                            + print[0].genders[i].gender + '" checked>'+'&nbsp;'+print[0].genders[i].gender+'</input></br>');
            }
            
            for (var i = 0; i < print[0].classifications.length; i++) {
                    $('#classificationCheckbox').append('<input type="checkbox" class="filter" id="classificationCheckboxes" value="' 
                            + print[0].classifications[i].classification + '" checked>'+'&nbsp; '+print[0].classifications[i].classification+'</input></br>');
            }
            
            for (var i = 0; i < print[0].gradeLevels.length; i++) {
                    $('#gradeLevelCheckbox').append('<input type="checkbox" class="filter" id="gradeLevelCheckboxes" value="' 
                            + print[0].gradeLevels[i].gradeLevel + '" checked>'+'&nbsp; '+print[0].gradeLevels[i].gradeLevel+'</input></br>');
            }
            
            
            if(chart=="0"||chart=="Line Chart"){
                drawElementaryEnrollment(print, 'spline', false);
            }
            else if(chart=="0"||chart=="Bar Chart"){
                drawElementaryEnrollment(print, 'column', false);
            }
            else if(chart=="0"||chart=="Stacked Bar Chart"){
                drawElementaryEnrollment(print, 'column', true);
            }
            else if(chart=="0"||chart=="Table"){
                drawElementaryEnrollmentTable(print);
            }
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });}

    function drawElementaryEnrollmentTable(print){
        var filteredOutClassifications = [];
        var filteredOutGenders = []; 
        var filteredOutGradeLevels = []; 
        $('[id="classificationCheckboxes"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutClassifications.push(id);
            }
        });

        $('[id="genderCheckboxes"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutGenders.push(id);
            }
        });
        
        $('[id="gradeLevelCheckboxes"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                
                filteredOutGradeLevels.push(id);
            }
        });
        
        var filteredOut = "";
        if(filteredOutGenders.length > 0){
            if(filteredOutGenders.length == 1){
                filteredOut += filteredOutGenders[0] + ' Students';
            } else if(filteredOutGenders.length > 1){
                for(var i = 0; i < filteredOutGenders.length; i++){
                    filteredOut += filteredOutGenders[i];
                    if(i == filteredOutGenders.length-2){
                        filteredOut += ' and ';
                    }
                    if(i == filteredOutGenders.length-1){
                       filteredOut += ' Students';
                    }
                }
            }
            if(filteredOutClassifications.length>0 && filteredOutGradeLevels.length>0){
                filteredOut += ', ';
            } else if (filteredOutClassifications.length>0 || filteredOutGradeLevels.length>0){
                filteredOut += ' and ';
            }
        }
        if(filteredOutGradeLevels.length>0){
            if(filteredOutGradeLevels.length == 1){
                filteredOut += "Grade Level "+filteredOutGradeLevels[0].substring(6);
            }
            else if (filteredOutGradeLevels.length > 1){
                filteredOut += "Grade Levels ";
                for(var i = 0; i < filteredOutGradeLevels.length; i++){
                    filteredOut += filteredOutGradeLevels[i].substring(6);
                    if(i == filteredOutGradeLevels.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutGradeLevels.length-2){
                        filteredOut += ', ';
                    }
                }
            }
            if(filteredOutClassifications.length>0){
                filteredOut += ' and ';
            }  
        }
        if(filteredOutClassifications.length>0){
            if(filteredOutClassifications.length == 1){
                filteredOut += filteredOutClassifications[0] + ' Schools';
            }
            else if (filteredOutClassifications.length > 1){
                for(var i = 0; i < filteredOutClassifications.length; i++){
                    filteredOut += filteredOutClassifications[i];
                    if(i == filteredOutClassifications.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutClassifications.length-2){
                        filteredOut += ', ';
                    }
                    if(i == filteredOutClassifications.length-1){
                       filteredOut += ' Schools';
                    }
                }
            }
        }
        if(filteredOut.length > 0){
            filteredOut = 'Without: '+ filteredOut;
        }
        
        if(chartSelected!= 'Stacked Bar Chart'){
            if(filteredOutGenders.length == 0){
                item = {};
                item["gender"] = 'Both Sexes';
                print[0].genders.push(item);
            } else {
                for(var b = 0; b < print[0].genders.length; b++){
                    if(print[0].genders[b].gender == 'Both Sexes'){
                        print[0].genders.splice(b, 1);
                    }
                }
            }
        }
        
        var zones = ["SOUTH", "NORTH"];
        var str = '<table id="dataTable" class="table table-hover table-bordered dataTable"> <thead id="thead">\n\
                            </thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
        document.getElementById("output").innerHTML = str;
        var zonesColspan = print[0].classifications.length*print[0].genders.length;
        var classificationColSpan = print[0].genders.length;
        var str = '<tr style="background-color: #454545; color: #fff">\n\
                                    <th rowspan="3"><center>School Year</center></th>';
        for(var c = 0; c < zones.length; c++){
            str+='<th colspan="'+zonesColspan+'"><center>'+zones[c].charAt(0)+zones[c].substring(1).toLowerCase()+' Caloocan</center></th>';
        }
        
        str+='</tr>';
        if(classificationColSpan > 0){
            str+='<tr style="background-color: #454545; color: #fff">';
            for(var i = 0; i < 2; i++){
                for(var c = 0; c < print[0].classifications.length; c++){
                    str+='<th colspan="'+classificationColSpan+'"><center>'+print[0].classifications[c].classification+'</center></th>';
                }
            }
            str+='</tr>';
        }
        str+='<tr style="background-color: #454545; color: #fff">';
        for(var i = 0; i < print[0].classifications.length*2; i++){
            for(var c = 0; c < print[0].genders.length; c++){
                if(print[0].genders[c].gender != 'Both Sexes'){
                    str+='<th><center>'+print[0].genders[c].gender+'</center></th>';
                } else{
                    str+='<th><center>Total</center></th>';
                }
            }
        }
        str+='</tr>';       
        $('#thead').append(str);
        
        
        
        for(var a = 0; a < print[0].years.length;a++){
            str = '<tr>';
            str+='<td><b>';
            str+=print[0].years[a].year+' - '+(parseInt(print[0].years[a].year)+1);
            str+='</b></td>';
            var totalMale = 0;
            var totalFemale = 0;
            for(var c = 0; c < zones.length; c++){
                for(var y = 0; y < print[0].classifications.length; y++){
                    for(var b = 0; b < print[0].genders.length; b++){
                        var total = 0;
                        for (var i = 0; i < print[0].people.length; i++) {
                            for(var j = 0; j < print[0].gradeLevels.length; j++){
                                if(print[0].gradeLevels[j].gradeLevel == print[0].people[i].gradeLevel){
                                    if(print[0].people[i].year == print[0].years[a].year){
                                        if(zones[c] == print[0].people[i].zone){
                                            if(print[0].classifications[y].classification == print[0].people[i].classification){
                                                if("Male" == print[0].genders[b].gender){
                                                    total += print[0].people[i].male;
                                                }
                                                if("Female" == print[0].genders[b].gender){
                                                    total += print[0].people[i].female;
                                                }
                                                if("Both Sexes" == print[0].genders[b].gender){
                                                    total += print[0].people[i].male + print[0].people[i].female;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        str+=('<td class="number">');
                        str+=(total);
                        str+=('</td>');
                    }
                }
                
            }
            str+=('</tr>');
            $('#data').append(str);    
        }
        
        format();
        
        $("#dataTable").DataTable({
            "paging": false,
            "ordering": false,
            "sDom": '<"header">',
            "info": false, "language": {
                "emptyTable": "No Data"
            }
        });
        
        $("div.header").html('<h3><center>Enrollment in Public and Private Elementary Schools</center></h3><center>'+filteredOut+'</center><br/>');
        
        var chart = $('#output').highcharts();
        chart.destroy();
    }

    function drawElementaryEnrollment(print, chart, isStacked){
        var filteredOutClassifications = [];
        var filteredOutGenders = []; 
        var filteredOutGradeLevels = []; 
        $('[id="classificationCheckboxes"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutClassifications.push(id);
            }
        });

        $('[id="genderCheckboxes"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutGenders.push(id);
            }
        });
        
        $('[id="gradeLevelCheckboxes"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                
                filteredOutGradeLevels.push(id);
            }
        });
        
        var filteredOut = "";
        if(filteredOutGenders.length > 0){
            if(filteredOutGenders.length == 1){
                filteredOut += filteredOutGenders[0] + ' Students';
            } else if(filteredOutGenders.length > 1){
                for(var i = 0; i < filteredOutGenders.length; i++){
                    filteredOut += filteredOutGenders[i];
                    if(i == filteredOutGenders.length-2){
                        filteredOut += ' and ';
                    }
                    if(i == filteredOutGenders.length-1){
                       filteredOut += ' Students';
                    }
                }
            }
            if(filteredOutClassifications.length>0 && filteredOutGradeLevels.length>0){
                filteredOut += ', ';
            } else if (filteredOutClassifications.length>0 || filteredOutGradeLevels.length>0){
                filteredOut += ' and ';
            }
        }
        if(filteredOutGradeLevels.length>0){
            if(filteredOutGradeLevels.length == 1){
                filteredOut += "Grade Level "+filteredOutGradeLevels[0].substring(6);
            }
            else if (filteredOutGradeLevels.length > 1){
                filteredOut += "Grade Levels ";
                for(var i = 0; i < filteredOutGradeLevels.length; i++){
                    filteredOut += filteredOutGradeLevels[i].substring(6);
                    if(i == filteredOutGradeLevels.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutGradeLevels.length-2){
                        filteredOut += ', ';
                    }
                }
            }
            if(filteredOutClassifications.length>0){
                filteredOut += ' and ';
            }  
        }
        if(filteredOutClassifications.length>0){
            if(filteredOutClassifications.length == 1){
                filteredOut += filteredOutClassifications[0] + ' Schools';
            }
            else if (filteredOutClassifications.length > 1){
                for(var i = 0; i < filteredOutClassifications.length; i++){
                    filteredOut += filteredOutClassifications[i];
                    if(i == filteredOutClassifications.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutClassifications.length-2){
                        filteredOut += ', ';
                    }
                    if(i == filteredOutClassifications.length-1){
                       filteredOut += ' Schools';
                    }
                }
            }
        }
        if(filteredOut.length > 0){
            filteredOut = 'Without: '+ filteredOut;
        }
        
        if(chartSelected!= 'Stacked Bar Chart'){
            if(filteredOutGenders.length == 0){
                item = {};
                item["gender"] = 'Both Sexes';
                print[0].genders.push(item);
            } else {
                for(var b = 0; b < print[0].genders.length; b++){
                    if(print[0].genders[b].gender == 'Both Sexes'){
                        print[0].genders.splice(b, 1);
                    }
                }
            }
        }
        
        var ultimateTotal = [];
        for(var b = 0; b < print[0].genders.length; b++){
            var totals = {};
            var total = [];
            totals['name'] = print[0].genders[b].gender;
            if(print[0].genders[b].gender == 'Female'){
                totals['color'] = '#FF9999';
            }
            for(var a = 0; a < print[0].years.length;a++){
                var item = {};
                var totalSum = 0;
                item["name"] = print[0].years[a].year;
                item["drilldown"] = print[0].years[a].year + print[0].genders[b].gender;
                for (var i = 0; i < print[0].people.length; i++) {
                    for(var j = 0; j < print[0].gradeLevels.length; j++){
                        if(print[0].gradeLevels[j].gradeLevel == print[0].people[i].gradeLevel){
                            if(print[0].people[i].year == print[0].years[a].year){
                                for(var y = 0; y < print[0].classifications.length; y++){
                                    if(print[0].classifications[y].classification == print[0].people[i].classification){
                                        if("Male" == print[0].genders[b].gender){
                                            totalSum += print[0].people[i].male;
                                        }
                                        if("Female" == print[0].genders[b].gender){
                                            totalSum += print[0].people[i].female;
                                        }
                                        if("Both Sexes" == print[0].genders[b].gender){
                                            totalSum += print[0].people[i].male + print[0].people[i].female;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                item["y"] = totalSum;
                total.push(item);
            }
            totals['data'] = total;
            ultimateTotal.push(totals);
        }
        
        var drilldowns = [];
        for(var b = 0; b < print[0].genders.length; b++){
            for(var a = 0; a < print[0].years.length;a++){
                var item = {};
                var data = [];
                var totalNorth = 0;
                var totalSouth = 0;
                item["name"] = print[0].genders[b].gender;
                item["id"] = print[0].years[a].year + print[0].genders[b].gender;
                for (var i = 0; i < print[0].people.length; i++) {
                    if(print[0].people[i].year == print[0].years[a].year){
                        for(var j = 0; j < print[0].gradeLevels.length; j++){
                            if(print[0].gradeLevels[j].gradeLevel == print[0].people[i].gradeLevel){
                                for(var y = 0; y < print[0].classifications.length; y++){
                                    if(print[0].classifications[y].classification == print[0].people[i].classification){
                                        if("male" == print[0].genders[b].gender.toLowerCase()){
                                            if(print[0].people[i].zone.toLowerCase() == 'north'){
                                                totalNorth += print[0].people[i].male;
                                            }
                                            if(print[0].people[i].zone.toLowerCase() == 'south'){
                                                totalSouth += print[0].people[i].male;
                                            }
                                        }
                                        if("female" == print[0].genders[b].gender.toLowerCase()){
                                            if(print[0].people[i].zone.toLowerCase() == 'north'){
                                                totalNorth += print[0].people[i].female;
                                            }
                                            if(print[0].people[i].zone.toLowerCase() == 'south'){
                                                totalSouth += print[0].people[i].female;
                                            }
                                        }
                                        if("both sexes" == print[0].genders[b].gender.toLowerCase()){
                                            if(print[0].people[i].zone.toLowerCase() == 'north'){
                                                totalNorth += print[0].people[i].male + print[0].people[i].female;
                                            }
                                            if(print[0].people[i].zone.toLowerCase() == 'south'){
                                                totalSouth += print[0].people[i].male + print[0].people[i].female;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                var item2 = {};
                item2['name'] = 'North';
                item2["y"] = totalNorth;
                item2["drilldown"] = 'north'+print[0].genders[b].gender+print[0].years[a].year;
                data.push(item2);


                var item2 = {};
                item2['name'] = 'South';
                item2["y"] = totalSouth;
                item2["drilldown"] = 'south'+print[0].genders[b].gender+print[0].years[a].year;
                data.push(item2);

                item['data'] = data;
                drilldowns.push(item);
            }
        }
        
        var zones = ['NORTH', 'SOUTH'];
        for(var c = 0; c < zones.length; c++){
            for(var b = 0; b < print[0].genders.length; b++){
                for(var a = 0; a < print[0].years.length;a++){
                    var item = {};
                    var data = [];
                    item["name"] = print[0].genders[b].gender;
                    item["id"] = zones[c].toLowerCase()+print[0].genders[b].gender+print[0].years[a].year;
                    for(var d = 0; d < print[0].districts.length; d++){
                        var total = 0;
                        for (var i = 0; i < print[0].people.length; i++) {
                            if(print[0].people[i].year == print[0].years[a].year){
                                for(var j = 0; j < print[0].gradeLevels.length; j++){
                                    if(print[0].gradeLevels[j].gradeLevel == print[0].people[i].gradeLevel){
                                        if(print[0].people[i].district == print[0].districts[d].district){
                                            for(var y = 0; y < print[0].classifications.length; y++){
                                                if(print[0].classifications[y].classification == print[0].people[i].classification){
                                                    if("Male" == print[0].genders[b].gender){
                                                        total += print[0].people[i].male;
                                                    }
                                                    if("Female" == print[0].genders[b].gender){
                                                        total += print[0].people[i].female;
                                                    }
                                                    if("Both Sexes" == print[0].genders[b].gender){    
                                                        total += print[0].people[i].male + print[0].people[i].female;    
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if(zones[c] == 'NORTH'){
                            if(print[0].districts[d].district.toLowerCase().includes(zones[c].toLowerCase())){
                                item2 = {};
                                item2["name"] = print[0].districts[d].district;
                                item2["y"] = total;
                                item2["drilldown"] = zones[c].toLowerCase()+print[0].genders[b].gender+print[0].years[a].year + print[0].districts[d].district;
                                data.push(item2);
                            }
                        }
                        else if(zones[c] == 'SOUTH'){
                            if(!print[0].districts[d].district.toLowerCase().includes('north')){
                                item2 = {};
                                item2["name"] = print[0].districts[d].district;
                                item2["y"] = total;
                                item2["drilldown"] = zones[c].toLowerCase()+print[0].genders[b].gender+print[0].years[a].year + print[0].districts[d].district;
                                data.push(item2);
                            }
                        }
                    }
                    item['data'] = data;
                    drilldowns.push(item);
                }
            }
        }
        
        for(var c = 0; c < zones.length; c++){
            for(var b = 0; b < print[0].genders.length; b++){
                for(var a = 0; a < print[0].years.length;a++){
                    for(var d = 0; d < print[0].districts.length; d++){
                        var item = {};
                        var data = [];
                        item["name"] = print[0].genders[b].gender;
                        item["id"] = zones[c].toLowerCase()+print[0].genders[b].gender+print[0].years[a].year + print[0].districts[d].district;
                        for(var e = 0; e < print[0].schools.length; e++){
                            var total = 0;
                            if(print[0].schools[e].district == print[0].districts[d].district){
                                for(var y = 0; y < print[0].classifications.length; y++){
                                    if(print[0].classifications[y].classification == print[0].schools[e].classification){
                                        for (var i = 0; i < print[0].people.length; i++) {
                                            if(print[0].people[i].year == print[0].years[a].year){
                                                for(var j = 0; j < print[0].gradeLevels.length; j++){
                                                    if(print[0].gradeLevels[j].gradeLevel == print[0].people[i].gradeLevel){
                                                        if(print[0].people[i].district == print[0].districts[d].district){
                                                            if(print[0].classifications[y].classification == print[0].people[i].classification){
                                                                if(print[0].schools[e].schoolName == print[0].people[i].schoolName){
                                                                    if("Male" == print[0].genders[b].gender){
                                                                        total += print[0].people[i].male;
                                                                    }
                                                                    if("Female" == print[0].genders[b].gender){
                                                                        total += print[0].people[i].female;
                                                                    }
                                                                    if("Both Sexes" == print[0].genders[b].gender){    
                                                                        total += print[0].people[i].male + print[0].people[i].female;    
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    item2 = {};
                                    item2["name"] = print[0].schools[e].schoolName;
                                    item2["y"] = total;
                                    item2["drilldown"] = zones[c].toLowerCase()+print[0].genders[b].gender+print[0].years[a].year + print[0].districts[d].district + print[0].schools[e].schoolName;
                                    data.push(item2);
                                    }
                                }
                            }
                        }
                        item['data'] = data;
                        drilldowns.push(item);
                    }
                }
            }
        }
        
        
        for(var c = 0; c < zones.length; c++){
            for(var b = 0; b < print[0].genders.length; b++){
                for(var a = 0; a < print[0].years.length;a++){
                    for(var d = 0; d < print[0].districts.length; d++){
                        for(var e = 0; e < print[0].schools.length; e++){
                            var total = 0;
                            if(print[0].schools[e].district == print[0].districts[d].district){
                                var item = {};
                                var data = [];
                                item["name"] = print[0].genders[b].gender;
                                item["id"] = zones[c].toLowerCase()+print[0].genders[b].gender+print[0].years[a].year + print[0].districts[d].district+print[0].schools[e].schoolName;
                                for (var i = 0; i < print[0].people.length; i++) {
                                    if(print[0].people[i].year == print[0].years[a].year){
                                        for(var j = 0; j < print[0].gradeLevels.length; j++){
                                            if(print[0].gradeLevels[j].gradeLevel == print[0].people[i].gradeLevel){
                                                if(print[0].people[i].district == print[0].districts[d].district){
                                                    for(var y = 0; y < print[0].classifications.length; y++){
                                                        if(print[0].classifications[y].classification == print[0].people[i].classification){
                                                            if(print[0].schools[e].schoolName == print[0].people[i].schoolName){
                                                                if("Male" == print[0].genders[b].gender){
                                                                    item2 = {};
                                                                    item2["name"] = print[0].people[i].gradeLevel;
                                                                    item2["y"] = print[0].people[i].male;
                                                                    data.push(item2);
                                                                }
                                                                if("Female" == print[0].genders[b].gender){
                                                                    item2 = {};
                                                                    item2["name"] = print[0].people[i].gradeLevel;
                                                                    item2["y"] = print[0].people[i].female;
                                                                    data.push(item2);
                                                                }
                                                                if("Both Sexes" == print[0].genders[b].gender){    
                                                                    item2 = {};
                                                                    item2["name"] = print[0].people[i].gradeLevel;
                                                                    item2["y"] = print[0].people[i].male + print[0].people[i].female;
                                                                    data.push(item2); 
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                item['data'] = data;
                                drilldowns.push(item);
                            }
                        }
                    }
                }
            }
        }
        
        
        if(isStacked == false){
            $('#output').highcharts({
                chart: {
                    type: chart,
                    drilled: false,
                    zoomType: 'xy',
                    panning: true,
                    panKey: 'shift',
                    resetZoomButton: {
                        position: {
                            align: 'right', // by default
                            verticalAlign: 'top', // by default
                            x: -40,
                            y: 10
                        },
                        relativeTo: 'chart'
                    }
                },
                title: {
                    text: 'Enrollment in Public and Private Elementary Schools'
                },
                subtitle: {
                    text: filteredOut + '<br>Click and drag to zoom in. Hold down shift key to pan.'
                },
                xAxis: {
                    type: 'category',
                },
                tooltip: {
                    formatter: function () {
                        return '<b>' + this.series.name + '</b><br/>' 
                                + this.point.name + ': '+ Highcharts.numberFormat(Math.abs(this.point.y), 0);
                    }
                },
                yAxis:{
                    title: {text:'Enrollment'}
                },
                series: ultimateTotal,
                drilldown: {
                    series: drilldowns
                    }
            });
        }
        else{
            $('#output').highcharts({
                chart: {
                    type: chart,
                    drilled: false,
                    zoomType: 'xy',
                    panning: true,
                    panKey: 'shift',
                    resetZoomButton: {
                        position: {
                            align: 'right', // by default
                            verticalAlign: 'top', // by default
                            x: -40,
                            y: 10
                        },
                        relativeTo: 'chart'
                    }
                },
                title: {
                    text: 'Enrollment in Public and Private Elementary Schools'
                },
                subtitle: {
                    text: filteredOut + '<br>Click and drag to zoom in. Hold down shift key to pan.'
                },
                xAxis: {
                    type: 'category'
                },
                tooltip: {
                    formatter: function () {
                        return '<b>' + this.series.name + '</b><br/>' 
                                + this.point.name + ': '+ Highcharts.numberFormat(Math.abs(this.point.y), 0);
                    }
                },plotOptions: {
                    series: {
                        stacking: 'normal'
                    }
                },
                yAxis:{
                    title: {text:'Enrollment'}
                },
                series: ultimateTotal,
                drilldown: {
                    series: drilldowns
                    }
            });
        }
    }

function setKinderEnrollments(chart){
    $.ajax({
        url: "KinderEnrollmentServlet",
        type: 'POST',
        dataType: "JSON",
        success: function(data){
            reportSelected = 'kinderEnrollment';
            print = data;
            chartSelected = chart;
            $('#years').empty();
            $('#genderCheckbox').empty();
            $('#classificationCheckbox').empty();
            
            print[0].genders.length = 0;
            item = {};
            item["gender"] = 'Female';
            print[0].genders.push(item);
            item = {};
            item["gender"] = 'Male';
            print[0].genders.push(item);
            
            for (var i =0; i <  print[0].years.length; i++) {
                $('#yearsCheckbox').append('<input type="checkbox" class="filter" id="yearCheckboxes" value="' 
                        +print[0].years[i].year+ '" checked>'
                        +'&nbsp;'+print[0].years[i].year+'</input></br>');
            }
            //gender
            for (var i = 0; i < print[0].genders.length; i++) {
                    $('#genderCheckbox').append('<input type="checkbox" class="filter" id="genderCheckboxes" value="' 
                            + print[0].genders[i].gender + '" checked>'+'&nbsp;'+print[0].genders[i].gender+'</input></br>');
            }
            
            for (var i = 0; i < print[0].classifications.length; i++) {
                    $('#classificationCheckbox').append('<input type="checkbox" class="filter" id="classificationCheckboxes" value="' 
                            + print[0].classifications[i].classification + '" checked>'+'&nbsp; '+print[0].classifications[i].classification+'</input></br>');
            }
            
            
            if(chart=="0"||chart=="Line Chart"){
                drawKinderEnrollment(print, 'spline', false);
            }
            else if(chart=="0"||chart=="Bar Chart"){
                drawKinderEnrollment(print, 'column', false);
            }
            else if(chart=="0"||chart=="Stacked Bar Chart"){
                drawKinderEnrollment(print, 'column', true);
            }
            else if(chart=="0"||chart=="Table"){
                drawKinderEnrollmentTable(print);
            }
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });}


    function drawKinderEnrollmentTable(print){
        var filteredOutClassifications = [];
        var filteredOutGenders = []; 
        $('[id="classificationCheckboxes"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutClassifications.push(id);
            }
        });

        $('[id="genderCheckboxes"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutGenders.push(id);
            }
        });
        
        var filteredOut = "";
        if(filteredOutGenders.length > 0){
            if(filteredOutGenders.length == 1){
                filteredOut += filteredOutGenders[0] + ' Students';
            } else if(filteredOutGenders.length > 1){
                for(var i = 0; i < filteredOutGenders.length; i++){
                    filteredOut += filteredOutGenders[i];
                    if(i == filteredOutGenders.length-2){
                        filteredOut += ' and ';
                    }
                    if(i == filteredOutGenders.length-1){
                       filteredOut += ' Students';
                    }
                }
            }
            
            if(filteredOutClassifications.length>0){
                filteredOut += ' and ';
            }
        }
        if(filteredOutClassifications.length>0){
            if(filteredOutClassifications.length == 1){
                filteredOut += filteredOutClassifications[0] + ' Schools';
            }
            else if (filteredOutClassifications.length > 1){
                for(var i = 0; i < filteredOutClassifications.length; i++){
                    filteredOut += filteredOutClassifications[i];
                    if(i == filteredOutClassifications.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutClassifications.length-2){
                        filteredOut += ', ';
                    }
                    if(i == filteredOutClassifications.length-1){
                       filteredOut += ' Schools';
                    }
                }
            }
        }
        if(filteredOut.length > 0){
            filteredOut = 'Without: '+ filteredOut;
        }
        
        if(chartSelected!= 'Stacked Bar Chart'){
            if(filteredOutGenders.length == 0){
                item = {};
                item["gender"] = 'Both Sexes';
                print[0].genders.push(item);
            } else {
                for(var b = 0; b < print[0].genders.length; b++){
                    if(print[0].genders[b].gender == 'Both Sexes'){
                        print[0].genders.splice(b, 1);
                    }
                }
            }
        }
        
        var zones = ["SOUTH", "NORTH"];
        var str = '<table id="dataTable" class="table table-hover table-bordered dataTable"> <thead id="thead">\n\
                            </thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
        document.getElementById("output").innerHTML = str;
        var zonesColspan = print[0].classifications.length*print[0].genders.length;
        var classificationColSpan = print[0].genders.length;
        var str = '<tr style="background-color: #454545; color: #fff">\n\
                                    <th rowspan="3"><center>School Year</center></th>';
        for(var c = 0; c < zones.length; c++){
            str+='<th colspan="'+zonesColspan+'"><center>'+zones[c].charAt(0)+zones[c].substring(1).toLowerCase()+' Caloocan</center></th>';
        }
        
        str+='</tr>';
        if(classificationColSpan > 0){
            str+='<tr style="background-color: #454545; color: #fff">';
            for(var i = 0; i < 2; i++){
                for(var c = 0; c < print[0].classifications.length; c++){
                    str+='<th colspan="'+classificationColSpan+'"><center>'+print[0].classifications[c].classification+'</center></th>';
                }
            }
            str+='</tr>';
        }
        str+='<tr style="background-color: #454545; color: #fff">';
        for(var i = 0; i < print[0].classifications.length*2; i++){
            for(var c = 0; c < print[0].genders.length; c++){
                if(print[0].genders[c].gender != 'Both Sexes'){
                    str+='<th><center>'+print[0].genders[c].gender+'</center></th>';
                } else{
                    str+='<th><center>Total</center></th>';
                }
            }
        }
        str+='</tr>';       
        $('#thead').append(str);
        
        
        
        for(var a = 0; a < print[0].years.length;a++){
            str = '<tr>';
            str+='<td><b>';
            str+=print[0].years[a].year+' - '+(parseInt(print[0].years[a].year)+1);
            str+='</b></td>';
            for(var c = 0; c < zones.length; c++){
                for(var y = 0; y < print[0].classifications.length; y++){
                    for(var b = 0; b < print[0].genders.length; b++){
                        var total = 0;
                        for (var i = 0; i < print[0].people.length; i++) {
                            if(print[0].people[i].year == print[0].years[a].year){
                                if(zones[c] == print[0].people[i].zone){
                                    if(print[0].classifications[y].classification == print[0].people[i].classification){
                                        if("Male" == print[0].genders[b].gender){
                                            total += print[0].people[i].male;
                                        }
                                        if("Female" == print[0].genders[b].gender){
                                            total += print[0].people[i].female;
                                        }
                                        if("Both Sexes" == print[0].genders[b].gender){
                                            total += print[0].people[i].male + print[0].people[i].female;
                                        }
                                    }
                                }
                            }
                        }
                        str+=('<td class="number">');
                        str+=(total);
                        str+=('</td>');
                    }
                }
                
            }
            str+=('</tr>');
            $('#data').append(str);    
        }
        
        format();
        
        $("#dataTable").DataTable({
            "paging": false,
            "ordering": false,
            "dom": '<"header">',
            "info": false, "language": {
                "emptyTable": "No Data"
            }
        });
        
        $("div.header").html('<h3><center>Enrollment in Public and Private Preschools</center></h3><center>'+filteredOut+'</center><br/>');
        
        var chart = $('#output').highcharts();
        chart.destroy();
    }
    
    function drawKinderEnrollment(print, chart, isStacked){
        var filteredOutClassifications = [];
        var filteredOutGenders = []; 
        $('[id="classificationCheckboxes"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutClassifications.push(id);
            }
        });

        $('[id="genderCheckboxes"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutGenders.push(id);
            }
        });
        
        var filteredOut = "";
        if(filteredOutGenders.length > 0){
            if(filteredOutGenders.length == 1){
                filteredOut += filteredOutGenders[0] + ' Students';
            } else if(filteredOutGenders.length > 1){
                for(var i = 0; i < filteredOutGenders.length; i++){
                    filteredOut += filteredOutGenders[i];
                    if(i == filteredOutGenders.length-2){
                        filteredOut += ' and ';
                    }
                    if(i == filteredOutGenders.length-1){
                       filteredOut += ' Students';
                    }
                }
            }
            
            if(filteredOutClassifications.length>0){
                filteredOut += ' and ';
            }
        }
        if(filteredOutClassifications.length>0){
            if(filteredOutClassifications.length == 1){
                filteredOut += filteredOutClassifications[0] + ' Schools';
            }
            else if (filteredOutClassifications.length > 1){
                for(var i = 0; i < filteredOutClassifications.length; i++){
                    filteredOut += filteredOutClassifications[i];
                    if(i == filteredOutClassifications.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutClassifications.length-2){
                        filteredOut += ', ';
                    }
                    if(i == filteredOutClassifications.length-1){
                       filteredOut += ' Schools';
                    }
                }
            }
        }
        if(filteredOut.length > 0){
            filteredOut = 'Without: '+ filteredOut;
        }
        
        if(chartSelected!= 'Stacked Bar Chart'){
            if(filteredOutGenders.length == 0){
                item = {};
                item["gender"] = 'Both Sexes';
                print[0].genders.push(item);
            } else {
                for(var b = 0; b < print[0].genders.length; b++){
                    if(print[0].genders[b].gender == 'Both Sexes'){
                        print[0].genders.splice(b, 1);
                    }
                }
            }
        }
        
        var ultimateTotal = [];
        for(var b = 0; b < print[0].genders.length; b++){
            var totals = {};
            var total = [];
            totals['name'] = print[0].genders[b].gender;
            if(print[0].genders[b].gender == 'Female'){
                totals['color'] = '#FF9999';
            }
            for(var a = 0; a < print[0].years.length;a++){
                var item = {};
                var totalSum = 0;
                item["name"] = print[0].years[a].year;
                item["drilldown"] = print[0].years[a].year + print[0].genders[b].gender;
                
                for (var i = 0; i < print[0].people.length; i++) {
                    if(print[0].people[i].year == print[0].years[a].year){
                        for(var y = 0; y < print[0].classifications.length; y++){
                            if(print[0].classifications[y].classification == print[0].people[i].classification){
                                if("Male" == print[0].genders[b].gender){
                                    totalSum += print[0].people[i].male;
                                }
                                if("Female" == print[0].genders[b].gender){
                                    totalSum += print[0].people[i].female;
                                }
                                if("Both Sexes" == print[0].genders[b].gender){
                                    totalSum += print[0].people[i].male + print[0].people[i].female;
                                }
                            }
                        }
                    }
                }
                item["y"] = totalSum;
                total.push(item);
            }
            totals['data'] = total;
            ultimateTotal.push(totals);
        }
        
        var drilldowns = [];
        for(var b = 0; b < print[0].genders.length; b++){
            for(var a = 0; a < print[0].years.length;a++){
                var item = {};
                var data = [];
                var totalNorth = 0;
                var totalSouth = 0;
                item["name"] = print[0].genders[b].gender;
                item["id"] = print[0].years[a].year + print[0].genders[b].gender;
                for (var i = 0; i < print[0].people.length; i++) {
                    if(print[0].people[i].year == print[0].years[a].year){
                        for(var y = 0; y < print[0].classifications.length; y++){
                            if(print[0].classifications[y].classification == print[0].people[i].classification){
                                if("Male" == print[0].genders[b].gender){
                                    if(print[0].people[i].zone.toLowerCase() == 'north'){
                                        totalNorth += print[0].people[i].male;
                                    }
                                    if(print[0].people[i].zone.toLowerCase() == 'south'){
                                        totalSouth += print[0].people[i].male;
                                    }
                                }
                                if("Female" == print[0].genders[b].gender){
                                    if(print[0].people[i].zone.toLowerCase() == 'north'){
                                        totalNorth += print[0].people[i].female;
                                    }
                                    if(print[0].people[i].zone.toLowerCase() == 'south'){
                                        totalSouth += print[0].people[i].female;
                                    }
                                }
                                if("Both Sexes" == print[0].genders[b].gender){
                                    if(print[0].people[i].zone.toLowerCase() == 'north'){
                                        totalNorth += print[0].people[i].male + print[0].people[i].female;
                                    }
                                    if(print[0].people[i].zone.toLowerCase() == 'south'){
                                        totalSouth += print[0].people[i].male + print[0].people[i].female;
                                    }
                                }
                            }
                        }
                    }
                }
                var item2 = {};
                item2['name'] = 'North';
                item2["y"] = totalNorth;
                item2["drilldown"] = 'north'+print[0].genders[b].gender+print[0].years[a].year;
                data.push(item2);

                var item2 = {};
                item2['name'] = 'South';
                item2["y"] = totalSouth;
                item2["drilldown"] = 'south'+print[0].genders[b].gender+print[0].years[a].year;
                data.push(item2);

                item['data'] = data;
                drilldowns.push(item);
            }
        }
        
        var zones = ['NORTH', 'SOUTH'];
        for(var c = 0; c < zones.length; c++){
            for(var b = 0; b < print[0].genders.length; b++){
                for(var a = 0; a < print[0].years.length;a++){
                    for(var d = 0; d < print[0].districts.length; d++){
                        var item = {};
                        var data = [];
                        item["name"] = print[0].genders[b].gender;
                        item["id"] = zones[c].toLowerCase()+print[0].genders[b].gender+print[0].years[a].year + print[0].districts[d].district;
                        var total = 0;
                        for (var i = 0; i < print[0].people.length; i++) {
                            if(print[0].people[i].year == print[0].years[a].year){
                                if(print[0].people[i].district == print[0].districts[d].district){
                                    for(var y = 0; y < print[0].classifications.length; y++){
                                        if(print[0].classifications[y].classification == print[0].people[i].classification){
                                            if("Male" == print[0].genders[b].gender){
                                                var item2 = {};
                                                item2['name'] = print[0].people[i].schoolName;
                                                item2["y"] = print[0].people[i].male;
                                                data.push(item2);
                                            }
                                            if("Female" == print[0].genders[b].gender){
                                                var item2 = {};
                                                item2['name'] = print[0].people[i].schoolName;
                                                item2["y"] = print[0].people[i].female;
                                                data.push(item2);
                                            }
                                            if("Both Sexes" == print[0].genders[b].gender){    
                                                var item2 = {};
                                                item2['name'] = print[0].people[i].schoolName;
                                                item2["y"] = print[0].people[i].male + print[0].people[i].female;;
                                                data.push(item2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        item['data'] = data;
                        drilldowns.push(item);
                    }
                }
            }
        }
        
        
        var zones = ['NORTH', 'SOUTH'];
        for(var c = 0; c < zones.length; c++){
            for(var b = 0; b < print[0].genders.length; b++){
                for(var a = 0; a < print[0].years.length;a++){
                    var item = {};
                    var data = [];
                    item["name"] = print[0].genders[b].gender;
                    item["id"] = zones[c].toLowerCase()+print[0].genders[b].gender+print[0].years[a].year;
                    for(var d = 0; d < print[0].districts.length; d++){
                        var total = 0;
                        for (var i = 0; i < print[0].people.length; i++) {
                            if(print[0].people[i].year == print[0].years[a].year){
                                if(print[0].people[i].district == print[0].districts[d].district){
                                    for(var y = 0; y < print[0].classifications.length; y++){
                                        if(print[0].classifications[y].classification == print[0].people[i].classification){
                                            if("Male" == print[0].genders[b].gender){
                                                total += print[0].people[i].male;
                                            }
                                            if("Female" == print[0].genders[b].gender){
                                                total += print[0].people[i].female;
                                            }
                                            if("Both Sexes" == print[0].genders[b].gender){    
                                                total += print[0].people[i].male + print[0].people[i].female;    
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if(zones[c] == 'NORTH'){
                            if(print[0].districts[d].district.toLowerCase().includes(zones[c].toLowerCase())){
                                item2 = {};
                                item2["name"] = print[0].districts[d].district;
                                item2["y"] = total;
                                item2["drilldown"] = zones[c].toLowerCase()+print[0].genders[b].gender+print[0].years[a].year + print[0].districts[d].district;
                                data.push(item2);
                            }
                        }
                        else if(zones[c] == 'SOUTH'){
                            if(!print[0].districts[d].district.toLowerCase().includes('north')){
                                item2 = {};
                                item2["name"] = print[0].districts[d].district;
                                item2["y"] = total;
                                item2["drilldown"] = zones[c].toLowerCase()+print[0].genders[b].gender+print[0].years[a].year + print[0].districts[d].district;
                                data.push(item2);
                            }
                        }
                    }
                    item['data'] = data;
                    drilldowns.push(item);
                }
            }
        }
        
        if(isStacked == false){
            $('#output').highcharts({
                chart: {
                    type: chart,
                    drilled: false,
                    zoomType: 'xy',
                    panning: true,
                    panKey: 'shift',
                    resetZoomButton: {
                        position: {
                            align: 'right', // by default
                            verticalAlign: 'top', // by default
                            x: -40,
                            y: 10
                        },
                        relativeTo: 'chart'
                    }
                },
                title: {
                    text: 'Enrollment in Public and Private Preschools' 
                },
                subtitle: {
                    text: filteredOut + '<br>Click and drag to zoom in. Hold down shift key to pan.'
                },
                xAxis: {
                    type: 'category',
                },
                tooltip: {
                    formatter: function () {
                        return '<b>' + this.series.name + '</b><br/>' 
                                + this.point.name + ': '+ Highcharts.numberFormat(Math.abs(this.point.y), 0);
                    }
                },
                yAxis:{
                    title: {text:'Enrollment'}
                },
                series: ultimateTotal,
                drilldown: {
                    series: drilldowns
                    }
            });
        } 
        else{
            $('#output').highcharts({
                chart: {
                    type: chart,
                    drilled: false,
                    zoomType: 'xy',
                    panning: true,
                    panKey: 'shift',
                    resetZoomButton: {
                        position: {
                            align: 'right', // by default
                            verticalAlign: 'top', // by default
                            x: -40,
                            y: 10
                        },
                        relativeTo: 'chart'
                    }
                },
                title: {
                    text: 'Enrollment in Public and Private Preschools'
                },
                subtitle: {
                    text: filteredOut + '<br>Click and drag to zoom in. Hold down shift key to pan.'
                },
                xAxis: {
                    type: 'category',
                },
                tooltip: {
                    formatter: function () {
                        return '<b>' + this.series.name + '</b><br/>' 
                                + this.point.name + ': '+ Highcharts.numberFormat(Math.abs(this.point.y), 0);
                    }
                },
                yAxis:{
                    title: {text:'Enrollment'}
                },
                plotOptions: {
                    series: {
                        stacking: 'normal'
                    }
                },
                series: ultimateTotal,
                drilldown: {
                    series: drilldowns
                    }
            });
        }
    }

function setHHPopAgeGroupSex (chart){
    $.ajax({
        url: "SetHHPopAgeGroupSex",
        type: 'POST',
        dataType: "JSON",
        success: function(data){
            reportSelected = 'ageGroup';
            print = data;
            chartSelected = chart;
            $('#years').empty();
            $('#districts').empty();
            $('#barangays').empty();
            $('#sex').empty();
            
            $('#years').append("<option selected disabled id='year' value='"+print[0].years[print[0].years.length-1].year+"'>Choose Year for Report</option>");
            for (var i = print[0].years.length-1; i >= 0; i--) {
                $('#years').append('<option id="year" value="' 
                        +print[0].years[i].year+ '">'
                        +print[0].years[i].year+'</option></br>');
            }
            //barangay
            for (var i = 0; i < print[0].barangays.length; i++) {
                    $('#barangays').append('<input type="checkbox" class="filter" id="barangayss" value="' 
                            + print[0].barangays[i].barangay + '" checked>'+'&nbsp;Barangay '+print[0].barangays[i].barangay+'</input></br>');
            }
            
            
            print[0].genders.length = 0;
            item = {};
            item["gender"] = 'Male';
            print[0].genders.push(item);
            item = {};
            item["gender"] = 'Female';
            print[0].genders.push(item);
            
            print[0].ageGroups.length = 0;
            item = {};
            item["ageGroup"] = 'Under 1';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '1 - 4';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '5 - 9';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '10 - 14';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '15 - 19';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '20 - 24';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '25 - 29';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '30 - 34';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '35 - 39';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '40 - 44';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '45 - 49';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '50 - 54';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '55 - 59';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '60 - 64';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '65 - 69';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '70 - 74';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '75 - 79';
            print[0].ageGroups.push(item);
            item = {};
            item["ageGroup"] = '80 and Over';
            print[0].ageGroups.push(item);
            if(chart=="0"||chart=="Table"){
                item = {};
                item["ageGroup"] = 'Caloocan City';
                print[0].ageGroups.push(item);
            }
            
            for (var i = 0; i < print[0].genders.length; i++) {
                    $('#sex').append('<input type="checkbox" class="filter" id="genders" value="' 
                            + print[0].genders[i].gender + '" checked>'+'&nbsp; '+print[0].genders[i].gender+'</input></br>');
            }
            
            var year = $('#years').find(":selected").val();
            
            if(chart=="0"||chart=="Population Pyramid"){
                drawHHPopPyramid(print, print[0].years[print[0].years.length-1].year);
            }
            else if(chart=="0"||chart=="Pie Chart"){
                drawHHPopAgeGroupSexPie(print, print[0].years[print[0].years.length-1].year, 'pie');
            }
            else if(chart=="0"||chart=="Bar Chart"){
                drawHHPopAgeGroupSexPie(print, print[0].years[print[0].years.length-1].year, 'column');
            }
            else if(chart=="0"||chart=="Table"){
                drawHHPopTable(print, print[0].years[print[0].years.length-1].year);
            }
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });}
            
    function drawHHPopTable(print, year){
         var filteredOutBarangays = [];
        var filteredOutGenders = []; 
        $('[id="barangayss"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutBarangays.push(id);
            }
        });

        $('[id="genders"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutGenders.push(id);
            }
        });
        
        var filteredOut = "";
        if(filteredOutGenders.length > 0){
            if(filteredOutGenders.length == 1){
                filteredOut += filteredOutGenders[0];
            } else if(filteredOutGenders.length > 1){
                for(var i = 0; i < filteredOutGenders.length; i++){
                    filteredOut += filteredOutGenders[i];
                    if(i == filteredOutGenders.length-2){
                        filteredOut += ' and ';
                    }
                }
            }
            
            if(filteredOutBarangays.length>0){
                filteredOut += ' and ';
            }
        }
        if(filteredOutBarangays.length>0){
            if(filteredOutBarangays.length == 1){
                filteredOut += 'Barangay ' + filteredOutBarangays[0];
            }
            else if (filteredOutBarangays.length > 1){
                filteredOut += 'Barangays ';
                for(var i = 0; i < filteredOutBarangays.length; i++){
                    filteredOut += filteredOutBarangays[i];
                    if(i == filteredOutBarangays.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutBarangays.length-2){
                        filteredOut += ', ';
                    }
                }
            }
        }
        if(filteredOut.length > 0){
            filteredOut = 'Without: '+ filteredOut;
        }
        
        if(filteredOutGenders.length == 0){
            item = {};
            item["gender"] = 'Both Sexes';
            print[0].genders.push(item);
        } else {
            for(var b = 0; b < print[0].genders.length; b++){
                if(print[0].genders[b].gender == 'Both Sexes'){
                    print[0].genders.splice(b, 1);
                }
            }
        }
        
        var str = '<table id="dataTable" class="table table-hover table-bordered dataTable"> <thead id="thead">\n\
                            </thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
        document.getElementById("output").innerHTML = str;
        var str = '<tr style="background-color: #454545; color: #fff">\n\
                                    <th>Age Group</th>';
        for(var c = 0; c < print[0].genders.length; c++){
            str+='<th>'+print[0].genders[c].gender+'</th>';
        }
        str += '<th>Ratio</th>';
        str+='</tr>';       
        $('#thead').append(str);
        
        var caloocanMale = 0;
        var caloocanFemale = 0;
        for(var a = 0; a < print[0].ageGroups.length; a++){
            var isThereAnOutlier = false;
            str = '<tr>';
            str+='<td><b>';
            str+=print[0].ageGroups[a].ageGroup.replace("-","to");
            str+='</b></td>';
            var totalMale = 0;
            var totalFemale = 0;
            for(var c = 0; c < print[0].genders.length; c++){
                var total = 0;
                var isOutlier = false;
                var add;
                if(print[0].genders[c].gender == "Both Sexes"){
                    add = 2;
                }
                else{
                    add = 1;
                }
                for(var i = 0; i < print[0].people.length; i+=add){
                    if(print[0].people[i].year == year){
                        if(print[0].ageGroups[a].ageGroup == print[0].people[i].ageGroup){
                            for(var b = 0; b < print[0].barangays.length; b++){
                                if(print[0].people[i].barangay == print[0].barangays[b].barangay){
                                        if(print[0].people[i].gender == print[0].genders[c].gender){
                                            total+=print[0].people[i].people;
                                            if(print[0].people[i].gender == "Male"){
                                                totalMale += print[0].people[i].people;
                                                caloocanMale +=print[0].people[i].people;
                                                if(print[0].people[i].isOutlier == true){
                                                    isOutlier = true;
                                                }
                                            } 
                                            else if(print[0].people[i].gender == "Female"){
                                                totalFemale += print[0].people[i].people;
                                                caloocanFemale +=print[0].people[i].people;
                                                if(print[0].people[i].isOutlier == true){
                                                    isOutlier = true;
                                                }
                                            } 

                                        }
                                    else if(print[0].genders[c].gender == "Both Sexes"){
                                        total += print[0].people[i].people + print[0].people[i+1].people;
                                        if(print[0].people[i].isOutlier == true|| print[0].people[i+1].isOutlier == true){
                                            isOutlier = true;
                                        }
                                    }
                                    
                                }
                            }  
                        }
                        else if(print[0].ageGroups[a].ageGroup=="Caloocan City"){
                            if(print[0].genders[c].gender == "Both Sexes"){
                                total = caloocanMale + caloocanFemale;
                            }
                            else if(print[0].genders[c].gender == "Male"){
                                total = caloocanMale;
                                totalMale = total;
                            }
                            else if(print[0].genders[c].gender == "Female"){
                                total = caloocanFemale;
                                totalFemale = total;
                            }
                        } 
                    }
                }
                if(isOutlier){
                    str+=('<td class="number" bgcolor="#D9534F" style="color:#FFFFFF">');
                    str+=(total);
                    str+=('</td>');
                    isThereAnOutlier = true;
                } else {
                    str+=('<td class="number">');
                    str+=(total);
                    str+=('</td>');
                }
            }
            var div = totalMale/totalFemale;
            var ratio = div*100;
            if(!isFinite(ratio)){
                ratio = 0;
            }
            if(isThereAnOutlier && ratio > 0){
                str+=('<td class="number" bgcolor="#D9534F" style="color:#FFFFFF">');
                str+=(ratio.toFixed(3));
                str+=('</td>');
            } else {
                str+=('<td class="number">');
                str+=(ratio.toFixed(3));
                str+=('</td>');
            }
            str+=('</tr>');
            $('#data').append(str);
//                if(isOutlier){
//                    item["color"] = "#FF0000";
//                }
            //item["y"] = -total;
        }
        
        format();
        $("#dataTable").DataTable({
            "paging": false,
            "ordering": false,
            "sDom": '<"header">',
            "info": false, "language": {
                "emptyTable": "No Data"
            }
        });
        
        $("div.header").html('<h3><center>Household Population by Age Group and Sex for '+year+'</center></h3><center>'+filteredOut+'</center><br/>');
        
        var chart = $('#output').highcharts();
        chart.destroy();
    }
    
    function drawHHPopPyramid(print, year){
        
        var filteredOutBarangays = [];
        var filteredOutGenders = []; 
        $('[id="barangayss"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutBarangays.push(id);
            }
        });

        $('[id="genders"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutGenders.push(id);
            }
        });
        
        var filteredOut = "";
        if(filteredOutGenders.length > 0){
            if(filteredOutGenders.length == 1){
                filteredOut += filteredOutGenders[0];
            } else if(filteredOutGenders.length > 1){
                for(var i = 0; i < filteredOutGenders.length; i++){
                    filteredOut += filteredOutGenders[i];
                    if(i == filteredOutGenders.length-2){
                        filteredOut += ' and ';
                    }
                }
            }
            
            if(filteredOutBarangays.length>0){
                filteredOut += ' and ';
            }
        }
        if(filteredOutBarangays.length>0){
            if(filteredOutBarangays.length == 1){
                filteredOut += 'Barangay ' + filteredOutBarangays[0];
            }
            else if (filteredOutBarangays.length > 1){
                filteredOut += 'Barangays ';
                for(var i = 0; i < filteredOutBarangays.length; i++){
                    filteredOut += filteredOutBarangays[i];
                    if(i == filteredOutBarangays.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutBarangays.length-2){
                        filteredOut += ', ';
                    }
                }
            }
        }
        if(filteredOut.length > 0){
            filteredOut = 'Without: '+ filteredOut;
        }

        var topCategories = [];
        for (var i = 0; i < print[0].ageGroups.length; i++) {
            topCategories.push(print[0].ageGroups[i].ageGroup);
        }
        
        var male = [];
        
        for(var a = 0; a < print[0].ageGroups.length; a++){
            var item = {};
            var total = 0;
            var isOutlier = false;
            item["name"] = print[0].ageGroups[a].ageGroup;
            item["drilldown"] = 'm'+year+print[0].ageGroups[a].ageGroup;
            for(var i = 0; i < print[0].people.length; i++){
                if(print[0].people[i].year == year){
                    if(print[0].ageGroups[a].ageGroup == print[0].people[i].ageGroup){
                        for(var c = 0; c < print[0].genders.length; c++){
                            if(print[0].people[i].gender == print[0].genders[c].gender){
                                if(print[0].people[i].gender == 'Male'){
                                    for(var b = 0; b < print[0].barangays.length; b++){
                                        if(print[0].people[i].barangay == print[0].barangays[b].barangay){
                                            total+=print[0].people[i].people;
                                            if(print[0].people[i].isOutlier == true){
                                                isOutlier = true;
                                            }
                                        }
                                    }
                                    
                                }
                            }
                        }
                    }
                }
            }
            if(isOutlier){
                item["color"] = "#FF0000";
            }
            item["y"] = -total;
            male.push(item);
        }
        
        
        var female = [];
        for(var a = 0; a < print[0].ageGroups.length; a++){
            var item = {};
            var total = 0;
            var isOutlier = false;
            item["name"] = print[0].ageGroups[a].ageGroup;
            item["drilldown"] = 'f'+year+print[0].ageGroups[a].ageGroup;
            for(var i = 0; i < print[0].people.length; i++){
                if(print[0].people[i].year == year){
                    if(print[0].ageGroups[a].ageGroup == print[0].people[i].ageGroup){
                        for(var c = 0; c < print[0].genders.length; c++){
                            if(print[0].people[i].gender == print[0].genders[c].gender){
                                if(print[0].people[i].gender == 'Female'){
                                    for(var b = 0; b < print[0].barangays.length; b++){
                                        if(print[0].people[i].barangay == print[0].barangays[b].barangay){
                                            total+=print[0].people[i].people;
                                            if(print[0].people[i].isOutlier == true){
                                                isOutlier = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(isOutlier){
                item["color"] = "#FF0000";
            }
            item["y"] = total;
            female.push(item);
        }
        
        
        var drilldowns = [];
        for(var a = 0; a < print[0].ageGroups.length; a++){
            var totalNorth = 0;
            var totalSouth = 0;
            var item = {};
            var total = 0;
            var data = [];
            var isNorthOutlier = false;
            var isSouthOutlier = false;
            item["name"] = 'Female';
            item["id"] = 'f'+year+print[0].ageGroups[a].ageGroup;
            for(var i = 0; i < print[0].people.length; i++){
                if(print[0].people[i].year == year){
                    if(print[0].ageGroups[a].ageGroup == print[0].people[i].ageGroup){
                        for(var c = 0; c < print[0].genders.length; c++){
                            if(print[0].people[i].gender == print[0].genders[c].gender){
                                if(print[0].people[i].gender == 'Female'){
                                    for(var b = 0; b < print[0].barangays.length; b++){
                                        if(print[0].people[i].barangay == print[0].barangays[b].barangay){
                                            if(print[0].people[i].zone == 'NORTH'){
                                                totalNorth+=print[0].people[i].people;
                                                if(print[0].people[i].isOutlier == true){
                                                    isNorthOutlier = true;
                                                }
                                            }
                                            else{
                                                totalSouth+=print[0].people[i].people;
                                                if(print[0].people[i].isOutlier == true){
                                                    isSouthOutlier = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            item2 = {};
            item2["name"] = 'South Caloocan';
            item2["y"] = totalSouth;
            item2["drilldown"] = 'fs'+year+print[0].ageGroups[a].ageGroup; // fn = female south
            if(isSouthOutlier){
                item2["color"] = "#FF0000";
            }else {
                item2["color"] = "#FF9999";
            }
            data.push(item2);
            
            item2 = {};
            item2["name"] = 'North Caloocan';
            item2["y"] = totalNorth;
            item2["drilldown"] = 'fn'+year+print[0].ageGroups[a].ageGroup; // fn = female north
            if(isNorthOutlier){
                item2["color"] = "#FF0000";
            } else {
                item2["color"] = "#FF9999";
            }
            data.push(item2);
            
            
            item['data'] = data;
            drilldowns.push(item);
        }
        
        
        for(var a = 0; a < print[0].ageGroups.length; a++){
            var totalNorth = 0;
            var totalSouth = 0;
            var item = {};
            var total = 0;
            var data = [];
            var isNorthOutlier = false
            var isSouthOutlier = false
            item["name"] = 'Male';
            item["id"] = 'm'+year+print[0].ageGroups[a].ageGroup;
            for(var i = 0; i < print[0].people.length; i++){
                if(print[0].people[i].year == year){
                    if(print[0].ageGroups[a].ageGroup == print[0].people[i].ageGroup){
                        for(var c = 0; c < print[0].genders.length; c++){
                            if(print[0].people[i].gender == print[0].genders[c].gender){
                                if(print[0].people[i].gender == 'Male'){
                                    for(var b = 0; b < print[0].barangays.length; b++){
                                        if(print[0].people[i].barangay == print[0].barangays[b].barangay){
                                            if(print[0].people[i].zone == 'NORTH'){
                                                totalNorth+=print[0].people[i].people;
                                                if(print[0].people[i].isOutlier == true){
                                                    isNorthOutlier = true;
                                                }
                                            }
                                            else if(print[0].people[i].zone == 'SOUTH'){
                                                totalSouth+=print[0].people[i].people;
                                                if(print[0].people[i].isOutlier == true){
                                                    isSouthOutlier = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item2 = {};
            item2["name"] = 'South Caloocan';
            item2["y"] = totalSouth;
            item2["drilldown"] = 'ms'+year+print[0].ageGroups[a].ageGroup; // ms = male south
            if(isSouthOutlier){
                item2["color"] = "#FF0000";
            } else {
                item2["color"] = "#7CB5EC";
            }
            data.push(item2);
            
            item2 = {};
            item2["name"] = 'North Caloocan';
            item2["y"] = totalNorth;
            item2["drilldown"] = 'mn'+year+print[0].ageGroups[a].ageGroup; // mn = male north
            if(isNorthOutlier){
                item2["color"] = "#FF0000";
            } else {
                item2["color"] = "#7CB5EC";
            }
            data.push(item2);
            
            item['data'] = data;
            drilldowns.push(item);
        }
        
        var zoness = ['NORTH', 'SOUTH'];
        for(var x = 0; x < print[0].genders.length; x++){
            for(var y = 0; y < zoness.length; y++){
                for(var a = 0; a < print[0].ageGroups.length; a++){
                    var item = {};
                    var data = [];
                    item["name"] = print[0].genders[x].gender;
                    item["id"] = (print[0].genders[x].gender.charAt(0)).toLowerCase()
                            +(zoness[y].charAt(0)).toLowerCase()
                            +year
                            +print[0].ageGroups[a].ageGroup; // mn = male north
                    for(var b = print[0].barangays.length-1; b >= 0; b--){
                        for(var i = 0; i < print[0].people.length; i++){
                            if(print[0].people[i].year == year){
                                if(print[0].ageGroups[a].ageGroup == print[0].people[i].ageGroup){
                                    for(var c = 0; c < print[0].genders.length; c++){
                                        if(print[0].people[i].gender == print[0].genders[c].gender){
                                            if(print[0].people[i].gender == print[0].genders[x].gender){
                                                if(print[0].people[i].barangay == print[0].barangays[b].barangay){
                                                    if(print[0].people[i].zone == zoness[y]){
                                                        item2 = {};
                                                        item2["name"] = 'Barangay ' + print[0].people[i].barangay;
                                                        item2["y"] = print[0].people[i].people;
                                                        if(print[0].people[i].isOutlier == true){
                                                            item2["color"] = "#FF0000";
                                                        } else {
                                                            if(print[0].genders[x].gender == 'Male'){
                                                                item2["color"] = "#7CB5EC";
                                                            } else {
                                                                item2["color"] = "#FF9999";
                                                            }
                                                        }
                                                        data.push(item2);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldowns.push(item);
                }
            }
        }
        
    
        // Create the chart
            $('#output').highcharts({
                chart: {
                    type: 'bar',
                    zoomType: 'xy',
                    panning: true,
                    panKey: 'shift',
                    resetZoomButton: {
                        position: {
                            align: 'right', // by default
                            verticalAlign: 'top', // by default
                            x: -40,
                            y: 10
                        },
                        relativeTo: 'chart'
                    }
                },
                title: {
                    text: 'Household Population by Age Group and Sex for ' + year
                },
                subtitle: {
                    text: filteredOut + '<br>Click and drag to zoom in. Hold down shift key to pan.'
                },
                plotOptions: {
                    series: {
                        dataLabels: {
                            stacking: 'normal'
                        }
                    }
                },
                xAxis: [{
                        type: 'category',
                         reversed: false,
                        labels: {
                            step: 1
                        }
                    }],
                yAxis:{
                    title: {text:'Population'},
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
                        return '<b>' + this.series.name + ', ' + this.point.name + '</b><br/>' +
                                'Population: ' + Highcharts.numberFormat(Math.abs(this.point.y), 0);
                    }
                },
                series: [{
                        name: 'Male',
                        data: male,
                        color: '#7CB5EC'
                    }, {
                        name: 'Female',
                        color: '#FF9999',
                        data: female
                    }],
                drilldown: {
                series: drilldowns
                }
            });
    }
    
    
    function setMaritalStatus(chart) {
        $.ajax({
        url: "SetMaritalStatusServlet",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {
            reportSelected = 'maritalStatus';
            print = data;
            chartSelected = chart;
            $('#years').empty();
            $('#districts').empty();
            $('#barangays').empty();
            $('#sex').empty();
            
            $('#years').append("<option selected disabled id='year' value='"+print[0].years[print[0].years.length-1].year+"'>Choose Year for Report</option>");
            for (var i = print[0].years.length-1; i >= 0; i--) {
                $('#years').append('<option id="year" value="' 
                        + print[0].years[i].year + '">'
                        +print[0].years[i].year+'</option></br>');
            }
            //barangay
            for (var i = 0; i < print[0].barangays.length; i++) {
                    $('#barangays').append('<input type="checkbox" class="filter" id="barangayss" value="' 
                            + print[0].barangays[i].barangay + '" checked>'+'&nbsp;Barangay '+print[0].barangays[i].barangay+'</input></br>');
            }
            
            
            print[0].genders.length = 0;
            item = {};
            item["gender"] = 'Male';
            print[0].genders.push(item);
            item = {};
            item["gender"] = 'Female';
            print[0].genders.push(item);
            
            for (var i = 0; i < print[0].genders.length; i++) {
                    $('#sex').append('<input type="checkbox" class="filter" id="genders" value="' 
                            + print[0].genders[i].gender + '" checked>'+'&nbsp; '+print[0].genders[i].gender+'</input></br>');
            }
            
            var year = $('#years').find(":selected").val();
            
            if(chart=="0"||chart=="Pie Chart"){
                drawMaritalStatusBar(print, year, 'pie');
            }
            else if(chart=="0"||chart=="Bar Chart"){
                drawMaritalStatusBar(print, year, 'column');
            }
            else if(chart=="0"||chart=="Table"){
                drawMaritalStatusTable(print, year);
            }
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
    }
    
    function drawHHPopAgeGroupSexPie(print, year, chart){
        var filteredOutBarangays = [];
        var filteredOutGenders = []; 
        $('[id="barangayss"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutBarangays.push(id);
            }
        });

        $('[id="genders"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutGenders.push(id);
            }
        });
        
        var filteredOut = "";
        if(filteredOutGenders.length > 0){
            if(filteredOutGenders.length == 1){
                filteredOut += filteredOutGenders[0];
            } else if(filteredOutGenders.length > 1){
                for(var i = 0; i < filteredOutGenders.length; i++){
                    filteredOut += filteredOutGenders[i];
                    if(i == filteredOutGenders.length-2){
                        filteredOut += ' and ';
                    }
                }
            }
            
            if(filteredOutBarangays.length>0){
                filteredOut += ' and ';
            }
        }
        if(filteredOutBarangays.length>0){
            if(filteredOutBarangays.length == 1){
                filteredOut += 'Barangay ' + filteredOutBarangays[0];
            }
            else if (filteredOutBarangays.length > 1){
                filteredOut += 'Barangays ';
                for(var i = 0; i < filteredOutBarangays.length; i++){
                    filteredOut += filteredOutBarangays[i];
                    if(i == filteredOutBarangays.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutBarangays.length-2){
                        filteredOut += ', ';
                    }
                }
            }
        }
        if(filteredOut.length > 0){
            filteredOut = 'Without: '+ filteredOut;
        }
        
            //color: Highcharts.getOptions().colors[0]
            var totals = [];
            for(var a = 0; a < print[0].ageGroups.length;a++){
                var item = {};
                var total = 0;
                item["name"] = print[0].ageGroups[a].ageGroup;
                item["drilldown"] = print[0].ageGroups[a].ageGroup+year;
                var isOutlier = false;
                for (var i = 0; i < print[0].people.length; i++) {
                    for(var y = 0; y < print[0].barangays.length; y++){
                        if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                            if(print[0].people[i].year == year){
                                for(var z = 0; z < print[0].genders.length; z++){
                                    if(print[0].people[i].gender == print[0].genders[z].gender){
                                        if(print[0].ageGroups[a].ageGroup == print[0].people[i].ageGroup){
                                            total+=print[0].people[i].people;
                                            if(print[0].people[i].isOutlier == true){
                                                isOutlier = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(isOutlier){
                    item["color"] = "#FF0000";;
                } else {
                    if(chart=="column"){
                        item["color"] = Highcharts.getOptions().colors[0];
                    } else{
                        //item["color"] = Highcharts.getOptions().colors[a];
                    }
                }
                item["y"] = total;
                totals.push(item);
            }
            
            var drilldowns = [];
            var zones = ['NORTH', 'SOUTH'];
            for(var a = 0; a < print[0].ageGroups.length;a++){
                var item = {};
                var totalNorth = 0;
                var isNorthOutlier = false;
                var totalSouth = 0;
                var isSouthOutlier = false;
                item["name"] = print[0].ageGroups[a].ageGroup;
                item["id"] = print[0].ageGroups[a].ageGroup+year;
                var data = [];
                for (var i = 0; i < print[0].people.length; i++) {
                    for(var y = 0; y < print[0].barangays.length; y++){
                        if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                            if(print[0].people[i].year == year){
                                for(var z = 0; z < print[0].genders.length; z++){
                                    if(print[0].people[i].gender == print[0].genders[z].gender){
                                        if(print[0].ageGroups[a].ageGroup == print[0].people[i].ageGroup){
                                            if(print[0].people[i].zone == 'NORTH'){
                                                totalNorth+=print[0].people[i].people;
                                                if(print[0].people[i].isOutlier == true){
                                                    isNorthOutlier = true;
                                                }
                                            }
                                            else{
                                                totalSouth+=print[0].people[i].people;
                                                if(print[0].people[i].isOutlier == true){
                                                    isSouthOutlier = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                var item2 = {};
                item2['name'] = 'North';
                item2["y"] = totalNorth;
                item2["drilldown"] = 'n'+print[0].ageGroups[a].ageGroup+year;
                if(isNorthOutlier){
                    item2["color"] = "#FF0000";
                } else {
                    if(chart=="column"){
                        item["color"] = Highcharts.getOptions().colors[0];
                    } else{
                        //item["color"] = Highcharts.getOptions().colors[a];
                    }
                }
                data.push(item2);

                var item2 = {};
                item2['name'] = 'South';
                item2["y"] = totalSouth;
                item2["drilldown"] = 's'+print[0].ageGroups[a].ageGroup+year;
                if(isSouthOutlier){
                    item2["color"] = "#FF0000";
                } else {
                    if(chart=="column"){
                        item["color"] = Highcharts.getOptions().colors[0];
                    } else{
                        //item["color"] = Highcharts.getOptions().colors[a];
                    }
                }
                data.push(item2);

                item['data'] = data;
                drilldowns.push(item);
            }
            
            var zoness = ['NORTH', 'SOUTH'];
            var genderLength = print[0].genders.length;
            for(var y = 0; y < zoness.length; y++){
                for(var a = 0; a < print[0].ageGroups.length; a++){
                    var add;
                    if(genderLength == 2 || genderLength == 0){
                        add = 2;
                    } else {
                        add = 1;
                    }
                    var item = {};
                    var data = [];
                    var total = 0;
                    var isOutlier = false;
                    item["name"] = print[0].ageGroups[a].ageGroup;
                    item["id"] = (zoness[y].charAt(0)).toLowerCase()
                            +print[0].ageGroups[a].ageGroup+year;
                    for(var b = 0; b < print[0].barangays.length; b++){
                        for(var i = 0; i < print[0].people.length; i+=add){
                            if(print[0].people[i].year == year){
                                if(print[0].ageGroups[a].ageGroup == print[0].people[i].ageGroup){
                                    if(print[0].people[i].barangay == print[0].barangays[b].barangay){
                                        if(print[0].people[i].zone == zoness[y]){
                                            if(print[0].genders.length == 2){
                                                item2 = {};
                                                item2["name"] = 'Barangay ' + print[0].barangays[b].barangay;
                                                item2["y"] = print[0].people[i].people + print[0].people[i+1].people;
                                                if(print[0].people[i].isOutlier == true||print[0].people[i+1].isOutlier == true){
                                                    item2["color"] = "#FF0000";
                                                } else {
                                                    if(chart=="column"){
                                                        item["color"] = Highcharts.getOptions().colors[0];
                                                    } else{
                                                        //item["color"] = Highcharts.getOptions().colors[a];
                                                    }
                                                }
                                                data.push(item2);
                                            } 
                                            else if(print[0].genders.length == 1){
                                                for(var c = 0; c < print[0].genders.length; c++){
                                                    if(print[0].people[i].gender == print[0].genders[c].gender){
                                                        item2 = {};
                                                        item2["name"] = 'Barangay ' + print[0].barangays[b].barangay;
                                                        item2["y"] = print[0].people[i].people;
                                                        if(print[0].people[i].isOutlier == true){
                                                            item2["color"] = "#FF0000";
                                                        } else {
                                                            if(chart=="column"){
                                                                item["color"] = Highcharts.getOptions().colors[0];
                                                            } else{
                                                                //item["color"] = Highcharts.getOptions().colors[a];
                                                            }
                                                        }
                                                        data.push(item2);
                                                    }
                                                }
                                            }
                                            else{
                                                item2 = {};
                                                item2["name"] = 'Barangay ' + print[0].barangays[b].barangay;
                                                item2["y"] = 0;
                                                data.push(item2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item['data'] = data;
                    drilldowns.push(item);
                }
            }
            
            
            $('#output').highcharts({
                chart: {
                    type: chart,
                    drilled: false,
                    zoomType: 'xy',
                    panning: true,
                    panKey: 'shift',
                    resetZoomButton: {
                        position: {
                            align: 'right', // by default
                            verticalAlign: 'top', // by default
                            x: -40,
                            y: 10
                        },
                        relativeTo: 'chart'
                    }
                },
                title: {
                    text: 'Household Population by Age Group and Sex for ' + year
                },
                subtitle: {
                    text: filteredOut + '<br>Click and drag to zoom in. Hold down shift key to pan.'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis:{
                    title: {text:'Population'}
                },
                series: [{
                    name: 'Caloocan City',
                    data: totals
                }],
                drilldown: {
                    series: drilldowns
                    }
            });
    }
    
    function setNutritionalStatus(chart) {
        $.ajax({
        url: "NutritionalStatusServlet",
        type: 'POST',
        dataType: "JSON",
        success: function (data) {
            reportSelected = 'nutritionalStatus';
            print = data;
            chartSelected = chart;
            $('#years').empty();
            $('#districts').empty();
            $('#gradeLevels').empty();
            $('#sex').empty();
            
            for (var i =0; i <  print[0].years.length; i++) {
                $('#yearsCheckbox').append('<input type="checkbox" class="filter" id="yearCheckboxes" value="' 
                        +print[0].years[i].year+ '" checked>'
                        +'&nbsp;'+print[0].years[i].year+'</input></br>');
            }
            //barangay
            for (var i = 0; i < print[0].gradeLevels.length; i++) {
                    $('#gradeLevelsBox').append('<input type="checkbox" class="filter" id="gradeLevels" value="' 
                            + print[0].gradeLevels[i].gradeLevel + '" checked>'+print[0].gradeLevels[i].gradeLevel+'</input></br>');
            }
            
            for (var i = 0; i < print[0].genders.length; i++) {
                    $('#sex').append('<input type="checkbox" class="filter" id="genders" value="' 
                            + print[0].genders[i].gender + '" checked>'+'&nbsp; '+print[0].genders[i].gender+'</input></br>');
            }
            
            var year = 2015;
            if(chart=="0"||chart=="Line Chart"){
                drawNutritionalStatus(print, 'spline', false);
            }
            else if(chart=="0"||chart=="Bar Chart"){
                drawNutritionalStatus(print, 'column', false);
            }
            else if(chart=="0"||chart=="Stacked Bar Chart"){
                drawNutritionalStatus(print, 'column', true);
            }
            else if(chart=="0"||chart=="Table"){
                drawNutritionalStatusTable(print);
            }
        },
        error: function (XMLHttpRequest, textStatus, exception) {
            alert(XMLHttpRequest.responseText);
        }
    });
    }
    
    function drawNutritionalStatusTable(print){
        var filteredOutGenders = []; 
        var filteredOutGradeLevels = []; 
        $('[id="genders"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutGenders.push(id);
            }
        });
        $('[id="gradeLevels"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                
                filteredOutGradeLevels.push(id);
            }
        });
        
        var filteredOut = "";
        if(filteredOutGenders.length > 0){
            if(filteredOutGenders.length == 1){
                filteredOut += filteredOutGenders[0] + ' Students';
            } else if(filteredOutGenders.length > 1){
                for(var i = 0; i < filteredOutGenders.length; i++){
                    filteredOut += filteredOutGenders[i];
                    if(i == filteredOutGenders.length-2){
                        filteredOut += ' and ';
                    }
                    if(i == filteredOutGenders.length-1){
                       filteredOut += ' Students';
                    }
                }
            }
            if(filteredOutGradeLevels.length>0){
                filteredOut += ' and ';
            }
        }
        if(filteredOutGradeLevels.length>0){
            if(filteredOutGradeLevels.length == 1){
                if(filteredOutGradeLevels[0]!= 'Pre Elementary' && filteredOutGradeLevels[0]!= 'SPED' ){
                    filteredOut += "Grade Level "+filteredOutGradeLevels[0].substring(6);
                } else {
                    filteredOut += filteredOutGradeLevels[0];
                }
            }
            else if (filteredOutGradeLevels.length > 1){
                if(filteredOutGradeLevels[0]!= 'Pre Elementary' && filteredOutGradeLevels[0]!= 'SPED' ){
                    filteredOut += "Grade Levels ";
                } 
                for(var i = 0; i < filteredOutGradeLevels.length; i++){
                    //filteredOut += filteredOutGradeLevels[i].substring(6);
                    if(filteredOutGradeLevels[i]!= 'Pre Elementary' && filteredOutGradeLevels[i]!= 'SPED' ){
                        filteredOut += filteredOutGradeLevels[i].substring(6);
                    } else {
                        filteredOut += filteredOutGradeLevels[i];
                    }
                    if(i == filteredOutGradeLevels.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutGradeLevels.length-2){
                        filteredOut += ', ';
                    }
                }
            }
        }
        if(filteredOut.length > 0){
            filteredOut = 'Without: '+ filteredOut;
        }
        
        var str = '<table id="dataTable" class="table table-hover table-bordered dataTable"> <thead id="thead">\n\
                            </thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
        document.getElementById("output").innerHTML = str;
        
        var str = '<tr style="background-color: #454545; color: #fff">\n\
                                    <th rowspan = "2">NutritionalStatus</th>';
        for(var i = 0; i < print[0].years.length; i++){
            str+='<th colspan="2"><b><center>'+print[0].years[i].year+'</center></b></th>';
        }
        str+='</tr>';
        str+='<tr style="background-color: #454545; color: #fff">';
        
        for(var i = 0; i < print[0].years.length; i++){
            str+='<th><b><center>Number</center></b></th>';
            str+='<th><b><center>%</center></b></th>';
        }
        str+='</tr>';
        $('#thead').append(str);
        
        var nutritionalStatus = ['Severely Wasted','Wasted', 'Normal', 'Overweight', 'Obese', 'Total Students Weighed','Established Number Students'];
        for(var a = 0; a < nutritionalStatus.length;a++){
            str = '<tr>';
            str+='<td><b>';
            str+=nutritionalStatus[a];
            str+='</b></td>';
            for(var c = 0; c < print[0].years.length; c++){
                var total=0;
                for (var i = 0; i < print[0].people.length; i++) {
                    if(print[0].people[i].year == print[0].years[c].year){
                        for(var z = 0; z < print[0].genders.length; z++){
                            if(print[0].people[i].gender == print[0].genders[z].gender){
                                for(var b = 0; b < print[0].gradeLevels.length; b++){
                                    if(print[0].people[i].gradeLevel == print[0].gradeLevels[b].gradeLevel){
                                        if(nutritionalStatus[a] == 'Severely Wasted'){
                                            total+=print[0].people[i].severelyWasted;
                                        }
                                        else if(nutritionalStatus[a] == 'Wasted'){
                                            total+=print[0].people[i].wasted;
                                        }
                                        else if(nutritionalStatus[a] == 'Normal'){
                                            total+=print[0].people[i].normal;
                                        }
                                        else if(nutritionalStatus[a] == 'Overweight'){
                                            total+=print[0].people[i].overweight;
                                        }
                                        else if(nutritionalStatus[a] == 'Obese'){
                                            total+=print[0].people[i].obese;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(nutritionalStatus[a]==  'Total Students Weighed'){
                    var ultimateTotal = 0;
                    for (var i = 0; i < print[0].people.length; i++) {
                        if(print[0].people[i].year == print[0].years[c].year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    for(var b = 0; b < print[0].gradeLevels.length; b++){
                                        if(print[0].people[i].gradeLevel == print[0].gradeLevels[b].gradeLevel){
                                            ultimateTotal+=print[0].people[i].obese + print[0].people[i].overweight + print[0].people[i].normal + print[0].people[i].wasted + print[0].people[i].severelyWasted;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    str+=('<td class="number">');
                    str+=(ultimateTotal);
                    str+=('</td>');

                    str+=('<td>');
                    str+=('</td>');
                }
                else if(nutritionalStatus[a]==  'Established Number Students'){
                    var ultimateTotal = 0;
                    for (var i = 0; i < print[0].enrollments.length; i++) {
                        if(print[0].enrollments[i].year == print[0].years[c].year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                for(var b = 0; b < print[0].gradeLevels.length; b++){
                                    if(print[0].enrollments[i].gradeLevel == print[0].gradeLevels[b].gradeLevel){
                                        if(print[0].genders[z].gender == 'Male'){
                                            ultimateTotal += print[0].enrollments[i].maleCount;
                                        }
                                        else if(print[0].genders[z].gender == 'Female'){
                                            ultimateTotal += print[0].enrollments[i].femaleCount;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    str+=('<td class="number">');
                    str+=(ultimateTotal);
                    str+=('</td>');

                    str+=('<td>');
                    str+=('</td>');
                }
                else{
                    var ultimateTotal = 0;
                    for (var i = 0; i < print[0].people.length; i++) {
                        if(print[0].people[i].year == print[0].years[c].year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    for(var b = 0; b < print[0].gradeLevels.length; b++){
                                        if(print[0].people[i].gradeLevel == print[0].gradeLevels[b].gradeLevel){
                                            ultimateTotal+=print[0].people[i].obese + print[0].people[i].overweight + print[0].people[i].normal + print[0].people[i].wasted + print[0].people[i].severelyWasted;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    str+=('<td class="number">');
                    str+=(total);
                    str+=('</td>');
                    
                    var percentage = (total/ultimateTotal)*100;
                    if(!isFinite(percentage)){
                        percentage =0;
                    }
                    str+=('<td class="number">');
                    str+=(percentage.toFixed(2)+'%');
                    str+=('</td>');
                } 
            }
            str+=('</tr>');
            $('#data').append(str);  
        }
        format();
        
        $("#dataTable").DataTable({
            "paging": false,
            "ordering": false,
            "sDom": '<"header">',
            "info": false, "language": {
                "emptyTable": "No Data"
            }
        });
        
        $("div.header").html('<h3><center>Nutritional Status of the Preschool and Elementary Students</center></h3><center>'+filteredOut+'</center><br/>');

        
        var chart = $('#output').highcharts();
        chart.destroy();
    }
    
    function drawNutritionalStatus(print, chart, isStacked){
        var filteredOutGenders = []; 
        var filteredOutGradeLevels = []; 
        $('[id="genders"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutGenders.push(id);
            }
        });
        $('[id="gradeLevels"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                
                filteredOutGradeLevels.push(id);
            }
        });
        
        var filteredOut = "";
        if(filteredOutGenders.length > 0){
            if(filteredOutGenders.length == 1){
                filteredOut += filteredOutGenders[0] + ' Students';
            } else if(filteredOutGenders.length > 1){
                for(var i = 0; i < filteredOutGenders.length; i++){
                    filteredOut += filteredOutGenders[i];
                    if(i == filteredOutGenders.length-2){
                        filteredOut += ' and ';
                    }
                    if(i == filteredOutGenders.length-1){
                       filteredOut += ' Students';
                    }
                }
            }
            if(filteredOutGradeLevels.length>0){
                filteredOut += ' and ';
            }
        }
        if(filteredOutGradeLevels.length>0){
            if(filteredOutGradeLevels.length == 1){
                if(filteredOutGradeLevels[0]!= 'Pre Elementary' && filteredOutGradeLevels[0]!= 'SPED' ){
                    filteredOut += "Grade Level "+filteredOutGradeLevels[0].substring(6);
                } else {
                    filteredOut += filteredOutGradeLevels[0];
                }
            }
            else if (filteredOutGradeLevels.length > 1){
                if(filteredOutGradeLevels[0]!= 'Pre Elementary' && filteredOutGradeLevels[0]!= 'SPED' ){
                    filteredOut += "Grade Levels ";
                } 
                for(var i = 0; i < filteredOutGradeLevels.length; i++){
                    //filteredOut += filteredOutGradeLevels[i].substring(6);
                    if(filteredOutGradeLevels[i]!= 'Pre Elementary' && filteredOutGradeLevels[i]!= 'SPED' ){
                        filteredOut += filteredOutGradeLevels[i].substring(6);
                    } else {
                        filteredOut += filteredOutGradeLevels[i];
                    }
                    if(i == filteredOutGradeLevels.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutGradeLevels.length-2){
                        filteredOut += ', ';
                    }
                }
            }
        }
        if(filteredOut.length > 0){
            filteredOut = 'Without: '+ filteredOut;
        }
        
        var ultimateTotal = [];
        var nutritionalStatus = ['Severely Wasted','Wasted', 'Normal', 'Overweight', 'Obese'];
        var totals = [];
        for(var a = 0; a < nutritionalStatus.length;a++){
            var totals = {};
            var total = [];
            totals['name'] = nutritionalStatus[a];
            for(var c = 0; c < print[0].years.length; c++){
                var item = {};
                var totalSum = 0;
                item["name"] = print[0].years[c].year;
                item["drilldown"] = nutritionalStatus[a]+print[0].years[c].year;
                for (var i = 0; i < print[0].people.length; i++) {
                    if(print[0].people[i].year == print[0].years[c].year){
                        for(var z = 0; z < print[0].genders.length; z++){
                            if(print[0].people[i].gender == print[0].genders[z].gender){
                                for(var b = 0; b < print[0].gradeLevels.length; b++){
                                    if(print[0].people[i].gradeLevel == print[0].gradeLevels[b].gradeLevel){
                                        if(nutritionalStatus[a] == 'Severely Wasted'){
                                            totalSum+=print[0].people[i].severelyWasted;
                                        }
                                        else if(nutritionalStatus[a] == 'Wasted'){
                                            totalSum+=print[0].people[i].wasted;
                                        }
                                        else if(nutritionalStatus[a] == 'Normal'){
                                            totalSum+=print[0].people[i].normal;
                                        }
                                        else if(nutritionalStatus[a] == 'Overweight'){
                                            totalSum+=print[0].people[i].overweight;
                                        }
                                        else if(nutritionalStatus[a] == 'Obese'){
                                            totalSum+=print[0].people[i].obese;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                item["y"] = totalSum;
                total.push(item);
            }
        totals['data'] = total;
        ultimateTotal.push(totals);
        }
            
        var drilldowns = [];
        var zones = ['NORTH', 'SOUTH', 'SPED'];
        for(var a = 0; a < nutritionalStatus.length;a++){
            for(var c = 0; c < print[0].years.length; c++){
                var item = {};
                var totalNorth = 0;
                var totalSouth = 0;
                var totalSPED = 0;
                item["name"] = nutritionalStatus[a];
                item["id"] = nutritionalStatus[a] + print[0].years[c].year;
                var data = [];
                for (var i = 0; i < print[0].people.length; i++) {
                    if(print[0].people[i].year == print[0].years[c].year){
                        for(var z = 0; z < print[0].genders.length; z++){
                            if(print[0].people[i].gender == print[0].genders[z].gender){
                                for(var b = 0; b < print[0].gradeLevels.length; b++){
                                    if(print[0].people[i].gradeLevel == print[0].gradeLevels[b].gradeLevel){
                                        if(nutritionalStatus[a] == 'Severely Wasted'){
                                            if(print[0].people[i].zone == 'NORTH'){
                                                totalNorth+=print[0].people[i].severelyWasted;
                                            }
                                            else if(print[0].people[i].zone == 'SOUTH'){
                                                totalSouth+=print[0].people[i].severelyWasted;
                                            }
                                            if(print[0].people[i].gradeLevel == 'SPED'){
                                                totalSPED+=print[0].people[i].severelyWasted;
                                            }
                                        }
                                        else if(nutritionalStatus[a] == 'Wasted'){
                                            if(print[0].people[i].zone == 'NORTH'){
                                                totalNorth+=print[0].people[i].wasted;
                                            }
                                            else if(print[0].people[i].zone == 'SOUTH'){
                                                totalSouth+=print[0].people[i].wasted;
                                            }
                                            if(print[0].people[i].gradeLevel == 'SPED'){
                                                totalSPED+=print[0].people[i].wasted;
                                            }
                                        }
                                        else if(nutritionalStatus[a] == 'Normal'){
                                            if(print[0].people[i].zone == 'NORTH'){
                                                totalNorth+=print[0].people[i].normal;
                                            }
                                            else if(print[0].people[i].zone == 'SOUTH'){
                                                totalSouth+=print[0].people[i].normal;
                                            }
                                            if(print[0].people[i].gradeLevel == 'SPED'){
                                                totalSPED+=print[0].people[i].normal;
                                            }
                                        }
                                        else if(nutritionalStatus[a] == 'Overweight'){
                                            if(print[0].people[i].zone == 'NORTH'){
                                                totalNorth+=print[0].people[i].overweight;
                                            }
                                            else if(print[0].people[i].zone == 'SOUTH'){
                                                totalSouth+=print[0].people[i].overweight;
                                            }
                                            if(print[0].people[i].gradeLevel == 'SPED'){
                                                totalSPED+=print[0].people[i].overweight;
                                            }
                                        }
                                        else if(nutritionalStatus[a] == 'Obese'){
                                            if(print[0].people[i].zone == 'NORTH'){
                                                totalNorth+=print[0].people[i].obese;
                                            }
                                            else if(print[0].people[i].zone == 'SOUTH'){
                                                totalSouth+=print[0].people[i].obese;                                           }
                                            if(print[0].people[i].gradeLevel == 'SPED'){
                                                totalSPED+=print[0].people[i].obese;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                var item2 = {};
                item2['name'] = 'North';
                item2["y"] = totalNorth;
                item2["drilldown"] = 'north'+nutritionalStatus[a]+print[0].years[c].year;;
                data.push(item2);

                var item2 = {};
                item2['name'] = 'South';
                item2["y"] = totalSouth;
                item2["drilldown"] = 'south'+nutritionalStatus[a]+print[0].years[c].year;;
                data.push(item2);

                var item2 = {};
                item2['name'] = 'SPED';
                item2["y"] = totalSPED;
                item2["drilldown"] = 'sped'+nutritionalStatus[a]+print[0].years[c].year;;
                data.push(item2);

                item['data'] = data;
                drilldowns.push(item);
            }
        }
            
        var genderLength = print[0].genders.length;
        var genderOne = '';
        if(genderLength == 1){
            genderOne = print[0].genders[0].gender;
        }
        if(genderLength > 0){
            for(var y = 0; y < zones.length; y++){
                for(var a = 0; a < nutritionalStatus.length;a++){
                    for(var d = 0; d < print[0].years.length; d++){
                        var add;
                        add = genderLength;
                        var item = {};
                        var data = [];

                        item["name"] = nutritionalStatus[a];
                        item["id"] = zones[y].toLowerCase()+nutritionalStatus[a]+print[0].years[d].year;
                        if(nutritionalStatus[a] == 'SPED'){
                            item["id"] = 'sped'+nutritionalStatus[a];
                        }
                        for(var b = 0; b < print[0].districts.length; b++){
                            var totalSum = 0;
                            for (var i = 0; i < print[0].people.length; i+=add) {
                                if(print[0].people[i].year == print[0].years[d].year){
                                        if(print[0].people[i].district == print[0].districts[b].district){
                                            if(print[0].people[i].zone == zones[y]){
                                                for(var c = 0; c < print[0].gradeLevels.length; c++){
                                                    if(print[0].people[i].gradeLevel == print[0].gradeLevels[c].gradeLevel){
                                                        if(print[0].genders.length == 2){
                                                            if(nutritionalStatus[a] == 'Severely Wasted'){
                                                                totalSum+= print[0].people[i].severelyWasted + print[0].people[i+1].severelyWasted;
                                                            } else if(nutritionalStatus[a] == 'Wasted'){
                                                                totalSum+= print[0].people[i].wasted + print[0].people[i+1].wasted;
                                                            } else if(nutritionalStatus[a] == 'Normal'){
                                                                totalSum+= print[0].people[i].normal + print[0].people[i+1].normal;
                                                            } else if(nutritionalStatus[a] == 'Overweight'){
                                                                totalSum+= print[0].people[i].overweight + print[0].people[i+1].overweight;
                                                            } else if(nutritionalStatus[a] == 'Obese'){
                                                                totalSum+= print[0].people[i].obese + print[0].people[i+1].obese;
                                                            }
                                                        }
                                                        else if(print[0].genders.length == 1){
                                                            if(print[0].people[i].gender == genderOne){
                                                                if(nutritionalStatus[a] == 'Severely Wasted'){
                                                                    totalSum+= print[0].people[i].severelyWasted;
                                                                } else if(nutritionalStatus[a] == 'Wasted'){
                                                                    totalSum+= print[0].people[i].wasted;
                                                                } else if(nutritionalStatus[a] == 'Normal'){
                                                                    totalSum+= print[0].people[i].normal;
                                                                } else if(nutritionalStatus[a] == 'Overweight'){
                                                                    totalSum+= print[0].people[i].overweight;
                                                                } else if(nutritionalStatus[a] == 'Obese'){
                                                                    totalSum+= print[0].people[i].obese;
                                                                }
                                                            }
                                                        }
                                                        else if (genderLength == 0){
                                                            totalSum = 0;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if(zones[y] == 'NORTH'){
                                    if(print[0].districts[b].district.toLowerCase().includes(zones[y].toLowerCase())){
                                        item2 = {};
                                        item2["name"] = print[0].districts[b].district;
                                        item2["y"] = totalSum;
                                        item2["drilldown"] = zones[y].toLowerCase()+nutritionalStatus[a]+print[0].districts[b].district + print[0].years[d].year;
                                        data.push(item2);
                                    }
                                }
                                else if(zones[y] == 'SPED'){
                                    if(print[0].districts[b].district==zones[y]) {
                                        item2 = {};
                                        item2["name"] = print[0].districts[b].district;
                                        item2["y"] = totalSum;
                                        item2["drilldown"] = zones[y].toLowerCase()+nutritionalStatus[a]+'sped' + print[0].years[d].year;
                                        data.push(item2);
                                    }
                                }
                                else if(zones[y] == 'SOUTH') {
                                    if(print[0].districts[b].district!=='SPED' && !(print[0].districts[b].district.toLowerCase().includes('north')) && print[0].districts[b].district!='Caloocan City'){
                                        item2 = {};
                                        item2["name"] = print[0].districts[b].district;
                                        item2["y"] = totalSum;
                                        item2["drilldown"] = zones[y].toLowerCase()+nutritionalStatus[a]+print[0].districts[b].district + print[0].years[d].year;
                                        data.push(item2);
                                    }
                                }
                                totalSum =0;
                            }
                        item['data'] = data;
                        drilldowns.push(item);
                    }
                }
            }
        }

        var genderLength = print[0].genders.length;
        if(genderLength > 0){
        for(var y = 0; y < zones.length; y++){
            for(var a = 0; a < nutritionalStatus.length;a++){
                for(var b = 0; b < print[0].districts.length; b++){
                    for(var d = 0; d < print[0].years.length; d++){
                        var add;
                        if(genderLength == 2 || genderLength == 0){
                            add = 2;
                        } else {
                            add = 1;
                        }
                        var item = {};
                        var data = [];

                        item["name"] = nutritionalStatus[a];
                        item["id"] = zones[y].toLowerCase()+nutritionalStatus[a]+print[0].districts[b].district + print[0].years[d].year;
                        if(nutritionalStatus[a] == 'SPED'){
                            item["id"] = 'sped'+nutritionalStatus[a]+print[0].districts[b].district;
                        }
                        var total = 0;
                        for (var i = 0; i < print[0].people.length; i+=add) {
                            if(print[0].people[i].year == print[0].years[d].year){
                                    if(print[0].people[i].district == print[0].districts[b].district){
                                        if(print[0].people[i].zone == zones[y]){
                                            for(var c = 0; c < print[0].gradeLevels.length; c++){
                                                if(print[0].people[i].gradeLevel == print[0].gradeLevels[c].gradeLevel){
                                                    if(print[0].genders.length == 2){
                                                        if(nutritionalStatus[a] == 'Severely Wasted'){
                                                            item2 = {};
                                                            item2["name"] = print[0].people[i].gradeLevel;
                                                            item2["y"] = print[0].people[i].severelyWasted + print[0].people[i+1].severelyWasted;
                                                            data.push(item2);
                                                        } else if(nutritionalStatus[a] == 'Wasted'){
                                                            item2 = {};
                                                            item2["name"] = print[0].people[i].gradeLevel;
                                                            item2["y"] = print[0].people[i].wasted + print[0].people[i+1].wasted;
                                                            data.push(item2);
                                                        } else if(nutritionalStatus[a] == 'Normal'){
                                                            item2 = {};
                                                            item2["name"] = print[0].people[i].gradeLevel;
                                                            item2["y"] = print[0].people[i].normal + print[0].people[i+1].normal;
                                                            data.push(item2);
                                                        } else if(nutritionalStatus[a] == 'Overweight'){
                                                            item2 = {};
                                                            item2["name"] = print[0].people[i].gradeLevel;
                                                            item2["y"] = print[0].people[i].overweight + print[0].people[i+1].overweight;
                                                            data.push(item2);
                                                        } else if(nutritionalStatus[a] == 'Obese'){
                                                            item2 = {};
                                                            item2["name"] = print[0].people[i].gradeLevel;
                                                            item2["y"] = print[0].people[i].obese + print[0].people[i+1].obese;
                                                            data.push(item2);
                                                        }
                                                    }
                                                    else if(print[0].genders.length == 1){
                                                        if(print[0].people[i].gender == genderOne){
                                                            if(nutritionalStatus[a] == 'Severely Wasted'){
                                                                item2 = {};
                                                                item2["name"] = print[0].people[i].gradeLevel;
                                                                item2["y"] = print[0].people[i].severelyWasted;
                                                                data.push(item2);
                                                            } else if(nutritionalStatus[a] == 'Wasted'){
                                                                item2 = {};
                                                                item2["name"] = print[0].people[i].gradeLevel;
                                                                item2["y"] = print[0].people[i].wasted;
                                                                data.push(item2);
                                                            } else if(nutritionalStatus[a] == 'Normal'){
                                                                item2 = {};
                                                                item2["name"] = print[0].people[i].gradeLevel;
                                                                item2["y"] = print[0].people[i].normal;
                                                                data.push(item2);
                                                            } else if(nutritionalStatus[a] == 'Overweight'){
                                                                item2 = {};
                                                                item2["name"] = print[0].people[i].gradeLevel;
                                                                item2["y"] = print[0].people[i].overweight;
                                                                data.push(item2);
                                                            } else if(nutritionalStatus[a] == 'Obese'){
                                                                item2 = {};
                                                                item2["name"] = print[0].people[i].gradeLevel;
                                                                item2["y"] = print[0].people[i].obese;
                                                                data.push(item2);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            item['data'] = data;
                            drilldowns.push(item);
                        }
                    }
                }
            }
        }
        
        
        if(isStacked==false){   
            $('#output').highcharts({
                chart: {
                    type: chart,
                    drilled: false,
                    zoomType: 'xy',
                    panning: true,
                    panKey: 'shift',
                    resetZoomButton: {
                        position: {
                            align: 'right', // by default
                            verticalAlign: 'top', // by default
                            x: -40,
                            y: 10
                        },
                        relativeTo: 'chart'
                    }
                },
                title: {
                    text: 'Nutritional Status of the Preschool and Elementary Students'
                },
                subtitle: {
                    text: filteredOut + '<br>Click and drag to zoom in. Hold down shift key to pan.'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis:{
                    title: {text:'Number of Students'}
                },
                series: ultimateTotal,
                    drilldown: {
                        series: drilldowns
                        }
            });
        }else {
            $('#output').highcharts({
                chart: {
                    type: chart,
                    drilled: false,
                    zoomType: 'xy',
                    panning: true,
                    panKey: 'shift',
                    resetZoomButton: {
                        position: {
                            align: 'right', // by default
                            verticalAlign: 'top', // by default
                            x: -40,
                            y: 10
                        },
                        relativeTo: 'chart'
                    }
                },
                title: {
                    text: 'Nutritional Status of the Preschool and Elementary Students'
                },
                subtitle: {
                    text: filteredOut + '<br>Click and drag to zoom in. Hold down shift key to pan.'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis:{
                    title: {text:'Number of Students'}
                },
                plotOptions: {
                    series: {
                        stacking: 'normal'
                    }
                },
                series: ultimateTotal,
                drilldown: {
                    series: drilldowns
                    }
            });
        }
    }
    
    function drawMaritalStatusTable(print, year){
        var filteredOutBarangays = [];
        var filteredOutGenders = []; 
        $('[id="barangayss"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutBarangays.push(id);
            }
        });

        $('[id="genders"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutGenders.push(id);
            }
        });
        
        var filteredOut = "";
        if(filteredOutGenders.length > 0){
            if(filteredOutGenders.length == 1){
                filteredOut += filteredOutGenders[0];
            } else if(filteredOutGenders.length > 1){
                for(var i = 0; i < filteredOutGenders.length; i++){
                    filteredOut += filteredOutGenders[i];
                    if(i == filteredOutGenders.length-2){
                        filteredOut += ' and ';
                    }
                }
            }
            
            if(filteredOutBarangays.length>0){
                filteredOut += ' and ';
            }
        }
        if(filteredOutBarangays.length>0){
            if(filteredOutBarangays.length == 1){
                filteredOut += 'Barangay ' + filteredOutBarangays[0];
            }
            else if (filteredOutBarangays.length > 1){
                filteredOut += 'Barangays ';
                for(var i = 0; i < filteredOutBarangays.length; i++){
                    filteredOut += filteredOutBarangays[i];
                    if(i == filteredOutBarangays.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutBarangays.length-2){
                        filteredOut += ', ';
                    }
                }
            }
        }
        if(filteredOut.length > 0){
            filteredOut = 'Without: '+ filteredOut;
        }
        
        if(filteredOutGenders.length == 0){
            item = {};
            item["gender"] = 'Both Sexes';
            print[0].genders.push(item);
        } else {
            for(var b = 0; b < print[0].genders.length; b++){
                if(print[0].genders[b].gender == 'Both Sexes'){
                    print[0].genders.splice(b, 1);
                }
            }
        }
        
        var maritalStatuses = ["Total Population 10 Yrs. Old & Over", "Single", "Married","Widowed", "Divorced/Separated", "Common Law/Live In", "Unknown"];
        var genders = ["Both Sexes", "Male", "Female"];
        
        var str = '<table id="dataTable" class="table table-hover table-bordered dataTable"> <thead id="thead">\n\
                            </thead>\n\
                            <tbody id="data">\n\
                            </tbody>\n\
                            </table>';
        document.getElementById("output").innerHTML = str;
        var str = '<tr style="background-color: #454545; color: #fff">\n\
                                    <th>Age Group</th>';
        for(var i = 0; i < maritalStatuses.length; i++){
            str+='<th>'+maritalStatuses[i]+'</th>';
        }
        str+='</tr>';       
        $('#thead').append(str);
        
        var str;
        
        for(var z = 0; z < print[0].genders.length; z++){
            var add;
            if(print[0].genders[z].gender == "Both Sexes"){
                add = 2;
            } else {
                add = 1;
            }
            str = '<tr>';
            str+='<td>';
            str+=print[0].genders[z].gender;
            str+='</td>';
            
            for(var a = 0; a < maritalStatuses.length; a++){
                var total = 0;
                var isOutlier = false;
                for (var i = 0; i < print[0].people.length; i+=add) {
                    for(var y = 0; y < print[0].barangays.length; y++){
                        if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                            if(print[0].people[i].year == year){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    if(maritalStatuses[a] == "Total Population 10 Yrs. Old & Over"){
                                        total+=print[0].people[i].people;
                                    }
                                    else if(maritalStatuses[a] == 'Single'){
                                        total+=print[0].people[i].single;
                                        if(print[0].people[i].isSingleOutlier){
                                            isOutlier = true;
                                        }
                                    }
                                    else if(maritalStatuses[a] == 'Married'){
                                        total+=print[0].people[i].married;
                                        if(print[0].people[i].isMarriedOutlier){
                                            isOutlier = true;
                                        }
                                    }
                                    else if(maritalStatuses[a] == 'Widowed'){
                                        total+=print[0].people[i].widowed;
                                        if(print[0].people[i].isWidowedOutlier){
                                            isOutlier = true;
                                        }
                                    }
                                    else if(maritalStatuses[a] == 'Divorced/Separated'){
                                        total+=print[0].people[i].divorced;
                                        if(print[0].people[i].isDivorcedOutlier){
                                            isOutlier = true;
                                        }
                                    }
                                    else if(maritalStatuses[a] == 'Common Law/Live In'){
                                        total+=print[0].people[i].liveIn;
                                        if(print[0].people[i].isLiveInOutlier){
                                            isOutlier = true;
                                        }
                                    }
                                    else if(maritalStatuses[a] == 'Unknown'){
                                        total+=print[0].people[i].unknown;
                                        if(print[0].people[i].isUnknownOutlier){
                                            isOutlier = true;
                                        }
                                    }
                                }
                                else if(print[0].genders[z].gender == "Both Sexes"){
                                    if(maritalStatuses[a] == "Total Population 10 Yrs. Old & Over"){
                                        total+=print[0].people[i].people + print[0].people[i+1].people;
                                    }
                                    else if(maritalStatuses[a] == 'Single'){
                                        total+=print[0].people[i].single + print[0].people[i+1].single;
                                        if(print[0].people[i].isSingleOutlier||print[0].people[i+1].isSingleOutlier){
                                            isOutlier = true;
                                        }
                                    }
                                    else if(maritalStatuses[a] == 'Married'){
                                        total+=print[0].people[i].married + print[0].people[i+1].married;
                                        if(print[0].people[i].isMarriedOutlier||print[0].people[i+1].isMarriedOutlier){
                                            isOutlier = true;
                                        }
                                    }
                                    else if(maritalStatuses[a] == 'Widowed'){
                                        total+=print[0].people[i].widowed + print[0].people[i+1].widowed;
                                        if(print[0].people[i].isWidowedOutlier||print[0].people[i+1].isWidowedOutlier){
                                            isOutlier = true;
                                        }
                                    }
                                    else if(maritalStatuses[a] == 'Divorced/Separated'){
                                        total+=print[0].people[i].divorced + print[0].people[i+1].divorced;
                                        if(print[0].people[i].isDivorcedOutlier||print[0].people[i+1].isDivorcedOutlier){
                                            isOutlier = true;
                                        }
                                    }
                                    else if(maritalStatuses[a] == 'Common Law/Live In'){
                                        total+=print[0].people[i].liveIn + print[0].people[i+1].liveIn;
                                        if(print[0].people[i].isLiveInOutlier||print[0].people[i+1].isLiveInOutlier){
                                            isOutlier = true;
                                        }
                                    }
                                    else if(maritalStatuses[a] == 'Unknown'){
                                        total+=print[0].people[i].unknown + print[0].people[i+1].unknown;
                                        if(print[0].people[i].isUnknownOutlier||print[0].people[i+1].isUnknownOutlier){
                                            isOutlier = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(isOutlier){
                    str+=('<td class="number" bgcolor="#D9534F" style="color:#FFFFFF">');
                } else{
                    str+=('<td class="number">');
                }
                str+=(total);
                str+=('</td>');
            }
            str+=('</tr>');
             $('#data').append(str);
        }
        format();
        
        $("#dataTable").DataTable({
            "paging": false,
            "ordering": false,
            "sDom": '<"header">',
            "info": false, "language": {
                "emptyTable": "No Data"
            }
        });
        
        $("div.header").html('<h3><center>Enrollment in Public and Private Elementary Schools for '+year+'</center></h3><center>'+filteredOut+'</center><br/>');
        
        var chart = $('#output').highcharts();
        chart.destroy();
    }
    
    function drawMaritalStatusBar(print, year, chart){
        var filteredOutBarangays = [];
        var filteredOutGenders = []; 
        $('[id="barangayss"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutBarangays.push(id);
            }
        });

        $('[id="genders"]').each(function (e) {
            if (!$(this).is(':checked')) {
                var id = $(this).attr('value');
                filteredOutGenders.push(id);
            }
        });
        
        var filteredOut = "";
        if(filteredOutGenders.length > 0){
            if(filteredOutGenders.length == 1){
                filteredOut += filteredOutGenders[0];
            } else if(filteredOutGenders.length > 1){
                for(var i = 0; i < filteredOutGenders.length; i++){
                    filteredOut += filteredOutGenders[i];
                    if(i == filteredOutGenders.length-2){
                        filteredOut += ' and ';
                    }
                }
            }
            
            if(filteredOutBarangays.length>0){
                filteredOut += ' and ';
            }
        }
        if(filteredOutBarangays.length>0){
            if(filteredOutBarangays.length == 1){
                filteredOut += 'Barangay ' + filteredOutBarangays[0];
            }
            else if (filteredOutBarangays.length > 1){
                filteredOut += 'Barangays ';
                for(var i = 0; i < filteredOutBarangays.length; i++){
                    filteredOut += filteredOutBarangays[i];
                    if(i == filteredOutBarangays.length-2){
                        filteredOut += ' and ';
                    } else if(i < filteredOutBarangays.length-2){
                        filteredOut += ', ';
                    }
                }
            }
        }
        if(filteredOut.length > 0){
            filteredOut = 'Without: '+ filteredOut;
        }
        
            var total = [];
            widowedItem = {};
            var widowedTotal = 0;
            widowedItem["name"] = 'Widowed';
            widowedItem["drilldown"] = 'widowed';
            var isOutlier = false;
            for (var i = 0; i < print[0].people.length; i++) {
                    for(var y = 0; y < print[0].barangays.length; y++){
                        if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                            if(print[0].people[i].year == year){
                                for(var z = 0; z < print[0].genders.length; z++){
                                    if(print[0].people[i].gender == print[0].genders[z].gender){
                                        if(print[0].people[i].isWidowedOutlier){
                                            isOutlier = true;
                                        }
                                        widowedTotal+=print[0].people[i].widowed;
                                    }
                                }
                            }
                        }
                    }
            }
            if(isOutlier){
                widowedItem["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    widowedItem["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
            widowedItem["y"] = widowedTotal;
            total.push(widowedItem);
            
            unknownItem = {};
            var unknownTotal = 0;
            unknownItem["name"] = 'Unknown';
            unknownItem["drilldown"] = 'unknown';
            var isOutlier = false;
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].barangays.length; y++){
                    if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                        if(print[0].people[i].year == year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    if(print[0].people[i].isUnknownOutlier){
                                        isOutlier = true;
                                    }
                                    unknownTotal+=print[0].people[i].unknown;
                                }
                            }
                        }
                    }
                }
            }
            if(isOutlier){
                unknownItem["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
            unknownItem["y"] = unknownTotal;
            total.push(unknownItem);
            
            marriedItem = {};
            var marriedTotal = 0;
            marriedItem["name"] = 'Married';
            marriedItem["drilldown"] = 'married';
            var isOutlier = false;
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].barangays.length; y++){
                    if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                        if(print[0].people[i].year == year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    if(print[0].people[i].isMarriedOutlier){
                                        isOutlier = true;
                                    }
                                    marriedTotal+=print[0].people[i].married;
                                }
                            }
                        }
                    }
                }
            }
            if(isOutlier){
                marriedItem["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    marriedItem["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
            marriedItem["y"] = marriedTotal;
            total.push(marriedItem);
            
            divorcedItem = {};
            var divorcedTotal = 0;
            divorcedItem["name"] = 'Divorced';
            divorcedItem["drilldown"] = 'divorced';
            var isOutlier = false;
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].barangays.length; y++){
                    if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                        if(print[0].people[i].year == year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    if(print[0].people[i].isDivorcedOutlier){
                                        isOutlier = true;
                                    }
                                    divorcedTotal+=print[0].people[i].divorced;
                                }
                            }
                        }
                    }
                }
            }
            if(isOutlier){
                divorcedItem["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    divorcedItem["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
            divorcedItem["y"] = divorcedTotal;
            total.push(divorcedItem);
            
            liveInItem = {};
            var liveInTotal = 0;
            liveInItem["name"] = 'Live-In';
            liveInItem["drilldown"] = 'liveIn';
            var isOutlier = false;
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].barangays.length; y++){
                    if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                        if(print[0].people[i].year == year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    if(print[0].people[i].isLiveInOutlier){
                                        isOutlier = true;
                                    }
                                    liveInTotal+=print[0].people[i].liveIn;
                                }
                            }
                        }
                    }
                }
            }
            if(isOutlier){
                liveInItem["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    liveInItem["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
            liveInItem["y"] = liveInTotal;
            total.push(liveInItem);
            
            singleItem = {};
            var singleTotal = 0;
            singleItem["name"] = 'Single';
            singleItem["drilldown"] = 'single';
            var isOutlier = false;
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].barangays.length; y++){
                    if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                        if(print[0].people[i].year == year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    if(print[0].people[i].isSingleOutlier){
                                        isOutlier = true;
                                    }
                                    singleTotal+=print[0].people[i].single;
                                }
                            }
                        }
                    }
                }
            }
            if(isOutlier){
                singleItem["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    singleItem["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
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
            var isNorthOutlier = false;
            var isSouthOutlier = false;
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].barangays.length; y++){
                    if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                        if(print[0].people[i].year == year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    if(print[0].people[i].zone=='NORTH'){
                                        north+=print[0].people[i].widowed;
                                        if(print[0].people[i].isWidowedOutlier){
                                            isNorthOutlier = true;
                                        }
                                    }
                                    else if(print[0].people[i].zone=='SOUTH'){
                                        south+=print[0].people[i].widowed;
                                        if(print[0].people[i].isWidowedOutlier){
                                            isSouthOutlier = true;
                                        }
                                    }
                                }
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
            if(isNorthOutlier){
                item2["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item2["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
            data.push(item2);
            item2 = {};
            if(isSouthOutlier){
                item2["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item2["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
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
            var isNorthOutlier = false;
            var isSouthOutlier = false;
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].barangays.length; y++){
                    if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                        if(print[0].people[i].year == year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    if(print[0].people[i].zone=='NORTH'){
                                        north+=print[0].people[i].married;
                                        if(print[0].people[i].isMarriedOutlier){
                                            isNorthOutlier = true;
                                        }
                                    }
                                    else if(print[0].people[i].zone=='SOUTH'){
                                        south+=print[0].people[i].married;
                                    if(print[0].people[i].isMarriedOutlier){
                                            isSouthOutlier = true;
                                        }
                                    }
                                }
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
            if(isNorthOutlier){
                item2["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item2["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
            data.push(item2);
            item2 = {};
            if(isSouthOutlier){
                item2["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item2["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
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
            var isNorthOutlier = false;
            var isSouthOutlier = false;
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].barangays.length; y++){
                    if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                        if(print[0].people[i].year == year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    if(print[0].people[i].zone=='NORTH'){
                                        north+=print[0].people[i].unknown;
                                        if(print[0].people[i].isUnknownOutlier){
                                            isNorthOutlier = true;
                                        }
                                    }
                                    else if(print[0].people[i].zone=='SOUTH'){
                                        south+=print[0].people[i].unknown;
                                        if(print[0].people[i].isUnknownOutlier){
                                            isSouthOutlier = true;
                                        }
                                    }
                                }
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
            if(isNorthOutlier){
                item2["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item2["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
            data.push(item2);
            item2 = {};
            if(isSouthOutlier){
                item2["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item2["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
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
            var isNorthOutlier = false;
            var isSouthOutlier = false;
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].barangays.length; y++){
                    if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                        if(print[0].people[i].year == year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    if(print[0].people[i].zone=='NORTH'){
                                        north+=print[0].people[i].divorced;
                                        if(print[0].people[i].isDivorcedOutlier){
                                            isNorthOutlier = true;
                                        }
                                    }
                                    else if(print[0].people[i].zone=='SOUTH'){
                                        south+=print[0].people[i].divorced;
                                        if(print[0].people[i].isDivorcedOutlier){
                                            isSouthOutlier = true;
                                        }
                                    }
                                }
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
            if(isNorthOutlier){
                item2["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item2["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
            data.push(item2);
            item2 = {};
            if(isSouthOutlier){
                item2["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item2["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
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
            var isNorthOutlier = false;
            var isSouthOutlier = false;
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].barangays.length; y++){
                    if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                        if(print[0].people[i].year == year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    if(print[0].people[i].zone=='NORTH'){
                                        north+=print[0].people[i].liveIn;
                                        if(print[0].people[i].isLiveInOutlier){
                                            isNorthOutlier = true;
                                        }
                                    }
                                    else if(print[0].people[i].zone=='SOUTH'){
                                        south+=print[0].people[i].liveIn;
                                        if(print[0].people[i].isLiveInOutlier){
                                            isSouthOutlier = true;
                                        }
                                    }
                                }
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
            if(isNorthOutlier){
                item2["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item2["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
            data.push(item2);
            item2 = {};
            if(isSouthOutlier){
                item2["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item2["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
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
            var isNorthOutlier = false;
            var isSouthOutlier = false;
            for (var i = 0; i < print[0].people.length; i++) {
                for(var y = 0; y < print[0].barangays.length; y++){
                    if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                        if(print[0].people[i].year == year){
                            for(var z = 0; z < print[0].genders.length; z++){
                                if(print[0].people[i].gender == print[0].genders[z].gender){
                                    if(print[0].people[i].zone=='NORTH'){
                                        north+=print[0].people[i].single;
                                        if(print[0].people[i].isSingleOutlier){
                                            isNorthOutlier = true;
                                        }
                                    }
                                    else if(print[0].people[i].zone=='SOUTH'){
                                        south+=print[0].people[i].single;
                                        if(print[0].people[i].isSingleOutlier){
                                            isSouthOutlier = true;
                                        }
                                    }
                                }
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
            if(isNorthOutlier){
                item2["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item2["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
            data.push(item2);
            item2 = {};
            if(isSouthOutlier){
                item2["color"] = "#FF0000";
            } else {
                if(chart=="column"){
                    item2["color"] = Highcharts.getOptions().colors[0];
                } else{
                    //item["color"] = Highcharts.getOptions().colors[a];
                }
            }
            item2["name"] = 'South';
            item2["y"] = south;
            item2["drilldown"] = 'singleSouth';
            data.push(item2);
            item['data'] = data;
            drilldowns.push(item);

            genderLength = print[0].genders.length;
            var zones = ["North", "South"];
            for(var a = 0; a < zones.length; a++){
                item={};
                item["id"] = 'liveIn'+zones[a];
                item["name"] = zones[a]+' Caloocan';
                data = [];
                var add;
                var isOutlier = false;
                if(genderLength == 2 || genderLength == 0){
                    add = 2;
                } else {
                    add = 1;
                }
                for (var i = 0; i < print[0].people.length; i+=add) {
                    for(var y = 0; y < print[0].barangays.length; y++){
                        if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                            if(print[0].people[i].year == year){
                                if(print[0].people[i].zone.toUpperCase() == zones[a].toUpperCase()){
                                    if(genderLength == 2){
                                        item2 = {};
                                        item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                        item2["y"] = print[0].people[i].liveIn + print[0].people[i+1].liveIn;
                                        if(print[0].people[i].isLiveInOutlier||print[0].people[i+1].isLiveInOutlier){
                                            item2["color"] = "#FF0000";
                                        } else {
                                            if(chart=="column"){
                                                item2["color"] = Highcharts.getOptions().colors[0];
                                            } else{
                                                //item["color"] = Highcharts.getOptions().colors[a];
                                            }
                                        }
                                        data.push(item2);
                                    }
                                    else if(genderLength == 1){
                                        for(var z = 0; z < print[0].genders.length; z++){
                                            if(print[0].people[i].gender == print[0].genders[z].gender){
                                                item2 = {};
                                                item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                                item2["y"] = print[0].people[i].liveIn;
                                                if(print[0].people[i].isLiveInOutlier){
                                                    item2["color"] = "#FF0000";
                                                } else {
                                                    if(chart=="column"){
                                                        item2["color"] = Highcharts.getOptions().colors[0];
                                                    } else{
                                                        //item["color"] = Highcharts.getOptions().colors[a];
                                                    }
                                                }
                                                data.push(item2);
                                            }
                                        }
                                    }
                                    else if(genderLength == 0){
                                        item2 = {};
                                        item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                        item2["y"] = 0;
                                        data.push(item2);
                                    }
                                }
                            }
                        }
                    }
                }
                item['data'] = data;
                drilldowns.push(item);
            }
            
            for(var a = 0; a < zones.length; a++){
                item={};
                item["id"] = 'single'+zones[a];
                item["name"] = zones[a]+' Caloocan';
                data = [];
                var add;
                var isOutlier = false;
                if(genderLength == 2 || genderLength == 0){
                    add = 2;
                } else {
                    add = 1;
                }
                for (var i = 0; i < print[0].people.length; i+=add) {
                    for(var y = 0; y < print[0].barangays.length; y++){
                        if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                            if(print[0].people[i].year == year){
                                if(print[0].people[i].zone.toUpperCase() == zones[a].toUpperCase()){
                                    if(genderLength == 2){
                                        item2 = {};
                                        item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                        item2["y"] = print[0].people[i].single + print[0].people[i+1].single;
                                        if(print[0].people[i].isSingleOutlier || print[0].people[i+1].isSingleOutlier){
                                            item2["color"] = "#FF0000";
                                        } else {
                                            if(chart=="column"){
                                                item2["color"] = Highcharts.getOptions().colors[0];
                                            } else{
                                                //item["color"] = Highcharts.getOptions().colors[a];
                                            }
                                        }
                                        data.push(item2);
                                    }
                                    else if(genderLength == 1){
                                        for(var z = 0; z < print[0].genders.length; z++){
                                            if(print[0].people[i].gender == print[0].genders[z].gender){
                                                item2 = {};
                                                item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                                item2["y"] = print[0].people[i].single;
                                                if(print[0].people[i].isSingleOutlier){
                                                    item2["color"] = "#FF0000";
                                                } else {
                                                    if(chart=="column"){
                                                        item2["color"] = Highcharts.getOptions().colors[0];
                                                    } else{
                                                        //item["color"] = Highcharts.getOptions().colors[a];
                                                    }
                                                }
                                                data.push(item2);
                                            }
                                        }
                                    }
                                    else if(genderLength == 0){
                                        item2 = {};
                                        item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                        item2["y"] = 0;
                                        data.push(item2);
                                    }
                                }
                            }
                        }
                    }
                }
                item['data'] = data;
                drilldowns.push(item);
            }
            
            for(var a = 0; a < zones.length; a++){
                item={};
                item["id"] = 'married'+zones[a];
                item["name"] = zones[a]+' Caloocan';
                data = [];
                var add;
                var isOutlier = false;
                if(genderLength == 2 || genderLength == 0){
                    add = 2;
                } else {
                    add = 1;
                }
                for (var i = 0; i < print[0].people.length; i+=add) {
                    for(var y = 0; y < print[0].barangays.length; y++){
                        if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                            if(print[0].people[i].year == year){
                                if(print[0].people[i].zone.toUpperCase() == zones[a].toUpperCase()){
                                    if(genderLength == 2){
                                        item2 = {};
                                        item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                        item2["y"] = print[0].people[i].married + print[0].people[i+1].married;
                                        if(print[0].people[i].isMarriedOutlier||print[0].people[i+1].isMarriedOutlier){
                                            item2["color"] = "#FF0000";
                                        } else {
                                            if(chart=="column"){
                                                item2["color"] = Highcharts.getOptions().colors[0];
                                            } else{
                                                //item["color"] = Highcharts.getOptions().colors[a];
                                            }
                                        }
                                        data.push(item2);
                                    }
                                    else if(genderLength == 1){
                                        for(var z = 0; z < print[0].genders.length; z++){
                                            if(print[0].people[i].gender == print[0].genders[z].gender){
                                                item2 = {};
                                                item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                                item2["y"] = print[0].people[i].married;
                                                if(print[0].people[i].isMarriedOutlier){
                                                    item2["color"] = "#FF0000";
                                                } else {
                                                    if(chart=="column"){
                                                        item2["color"] = Highcharts.getOptions().colors[0];
                                                    } else{
                                                        //item["color"] = Highcharts.getOptions().colors[a];
                                                    }
                                                }
                                                data.push(item2);
                                            }
                                        }
                                    }
                                    else if(genderLength == 0){
                                        item2 = {};
                                        item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                        item2["y"] = 0;
                                        data.push(item2);
                                    }
                                }
                            }
                        }
                    }
                }
                item['data'] = data;
                drilldowns.push(item);
            }
            
            for(var a = 0; a < zones.length; a++){
                item={};
                item["id"] = 'divorced'+zones[a];
                item["name"] = zones[a]+' Caloocan';
                data = [];
                var add;
                var isOutlier = false;
                if(genderLength == 2 || genderLength == 0){
                    add = 2;
                } else {
                    add = 1;
                }
                for (var i = 0; i < print[0].people.length; i+=add) {
                    for(var y = 0; y < print[0].barangays.length; y++){
                        if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                            if(print[0].people[i].year == year){
                                if(print[0].people[i].zone.toUpperCase() == zones[a].toUpperCase()){
                                    if(genderLength == 2){
                                        item2 = {};
                                        item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                        item2["y"] = print[0].people[i].divorced + print[0].people[i+1].divorced;
                                        if(print[0].people[i].isDivorcedOutlier||print[0].people[i+1].isDivorcedOutlier){
                                            item2["color"] = "#FF0000";
                                        } else {
                                            if(chart=="column"){
                                                item2["color"] = Highcharts.getOptions().colors[0];
                                            } else{
                                                //item["color"] = Highcharts.getOptions().colors[a];
                                            }
                                        }
                                        data.push(item2);
                                    }
                                    else if(genderLength == 1){
                                        for(var z = 0; z < print[0].genders.length; z++){
                                            if(print[0].people[i].gender == print[0].genders[z].gender){
                                                item2 = {};
                                                item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                                item2["y"] = print[0].people[i].divorced;
                                                if(print[0].people[i].isDivorcedOutlier){
                                                    item2["color"] = "#FF0000";
                                                } else {
                                                    if(chart=="column"){
                                                        item2["color"] = Highcharts.getOptions().colors[0];
                                                    } else{
                                                        //item["color"] = Highcharts.getOptions().colors[a];
                                                    }
                                                }
                                                data.push(item2);
                                            }
                                        }
                                    }
                                    else if(genderLength == 0){
                                        item2 = {};
                                        item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                        item2["y"] = 0;
                                        data.push(item2);
                                    }
                                }
                            }
                        }
                    }
                }
                item['data'] = data;
                drilldowns.push(item);
            }
            
            for(var a = 0; a < zones.length; a++){
                item={};
                item["id"] = 'unknown'+zones[a];
                item["name"] = zones[a]+' Caloocan';
                data = [];
                var add;
                var isOutlier = false;
                if(genderLength == 2 || genderLength == 0){
                    add = 2;
                } else {
                    add = 1;
                }
                for (var i = 0; i < print[0].people.length; i+=add) {
                    for(var y = 0; y < print[0].barangays.length; y++){
                        if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                            if(print[0].people[i].year == year){
                                if(print[0].people[i].zone.toUpperCase() == zones[a].toUpperCase()){
                                    if(genderLength == 2){
                                        item2 = {};
                                        item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                        item2["y"] = print[0].people[i].unknown + print[0].people[i+1].unknown;
                                        if(print[0].people[i].isUnknownOutlier||print[0].people[i+1].isUnknownOutlier){
                                            item2["color"] = "#FF0000";
                                        } else {
                                            if(chart=="column"){
                                                item2["color"] = Highcharts.getOptions().colors[0];
                                            } else{
                                                //item["color"] = Highcharts.getOptions().colors[a];
                                            }
                                        }
                                        data.push(item2);
                                    }
                                    else if(genderLength == 1){
                                        for(var z = 0; z < print[0].genders.length; z++){
                                            if(print[0].people[i].gender == print[0].genders[z].gender){
                                                item2 = {};
                                                item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                                item2["y"] = print[0].people[i].unknown;
                                                if(print[0].people[i].isUnknownOutlier){
                                                    item2["color"] = "#FF0000";
                                                } else {
                                                    if(chart=="column"){
                                                        item2["color"] = Highcharts.getOptions().colors[0];
                                                    } else{
                                                        //item["color"] = Highcharts.getOptions().colors[a];
                                                    }
                                                }
                                                data.push(item2);
                                            }
                                        }
                                    }
                                    else if(genderLength == 0){
                                        item2 = {};
                                        item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                        item2["y"] = 0;
                                        data.push(item2);
                                    }
                                }
                            }
                        }
                    }
                }
                item['data'] = data;
                drilldowns.push(item);
            }
            
            for(var a = 0; a < zones.length; a++){
                item={};
                item["id"] = 'widowed'+zones[a];
                item["name"] = zones[a]+' Caloocan';
                data = [];
                var add;
                var isOutlier = false;
                if(genderLength == 2 || genderLength == 0){
                    add = 2;
                } else {
                    add = 1;
                }
                for (var i = 0; i < print[0].people.length; i+=add) {
                    for(var y = 0; y < print[0].barangays.length; y++){
                        if(print[0].people[i].barangay == print[0].barangays[y].barangay){
                            if(print[0].people[i].year == year){
                                if(print[0].people[i].zone.toUpperCase() == zones[a].toUpperCase()){
                                    if(genderLength == 2){
                                        item2 = {};
                                        item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                        item2["y"] = print[0].people[i].widowed + print[0].people[i+1].widowed;
                                        if(print[0].people[i].isWidowedOutlier||print[0].people[i+1].isWidowedOutlier){
                                            item2["color"] = "#FF0000";
                                        } else {
                                            if(chart=="column"){
                                                item2["color"] = Highcharts.getOptions().colors[0];
                                            } else{
                                                //item["color"] = Highcharts.getOptions().colors[a];
                                            }
                                        }
                                        data.push(item2);
                                    }
                                    else if(genderLength == 1){
                                        for(var z = 0; z < print[0].genders.length; z++){
                                            if(print[0].people[i].gender == print[0].genders[z].gender){
                                                item2 = {};
                                                item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                                item2["y"] = print[0].people[i].widowed;
                                                if(print[0].people[i].isWidowedOutlier){
                                                    item2["color"] = "#FF0000";
                                                } else {
                                                    if(chart=="column"){
                                                        item2["color"] = Highcharts.getOptions().colors[0];
                                                    } else{
                                                        //item["color"] = Highcharts.getOptions().colors[a];
                                                    }
                                                }
                                                data.push(item2);
                                            }
                                        }
                                    }
                                    else if(genderLength == 0){
                                        item2 = {};
                                        item2["name"] =  'Barangay '+print[0].people[i].barangay;
                                        item2["y"] = 0;
                                        data.push(item2);
                                    }
                                }
                            }
                        }
                    }
                }
                item['data'] = data;
                drilldowns.push(item);
            }
            
            $('#output').highcharts({
                chart: {
                    type: chart,
                    drilled: false,
                    zoomType: 'xy',
                    panning: true,
                    panKey: 'shift',
                    resetZoomButton: {
                        position: {
                            align: 'right', // by default
                            verticalAlign: 'top', // by default
                            x: -40,
                            y: 10
                        },
                        relativeTo: 'chart'
                    }
                },
                title: {
                    text: 'Household Population 10 Yrs Old and Over by Age Group, Sex, and Marital Status for ' + year
                },
                subtitle: {
                    text: filteredOut + '<br>Click and drag to zoom in. Hold down shift key to pan.'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis:{
                    title: {text:'Population'}
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
                panKey: 'shift',
                resetZoomButton: {
                    position: {
                        align: 'right', // by default
                        verticalAlign: 'top', // by default
                        x: -40,
                        y: 10
                    },
                    relativeTo: 'chart'
                }
            },
            title: {
                text: 'Classroom Requirements in Public Elementary School'
            },
            xAxis: {
                type: 'category'
            },
            yAxis:{
                title: {text:'Values'}
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
                panKey: 'shift',
                resetZoomButton: {
                    position: {
                        align: 'right', // by default
                        verticalAlign: 'top', // by default
                        x: -40,
                        y: 10
                    },
                    relativeTo: 'chart'
                }
            },
            title: {
                text: 'Classroom Requirements in Public Elementary School'
            },
            xAxis: {
                type: 'category'
            },
            yAxis:{
                title: {text:'Values'}
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

