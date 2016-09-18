<%--
    Document   : eKinderPublic
    Created on : Jun 15, 2016, 10:23:41 PM
    Author     : Geraldine
--%>

<%@page import="ModelEducation.directorySchool"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--IMPORTING HTML IMPORTS (bootstrap + scripts)-->
<%@include file="../levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Facility | Number of Teachers and Classrooms for Private Schools</title>
        <link href="cssImported/search.css" rel="stylesheet" type="text/css"/>
        <link href="cssImported/uploadJSP.css" rel="stylesheet" type="text/css"/>
        <!--AUTO COMPLETE-->
        <script src="jsImported/jquery.autocomplete.js" type="text/javascript"></script>
        <script src="jsEducImports/searchSchoolPrivate.js" type="text/javascript"></script>
        <!--GEt YEAR-->
        <script src="jsImported/getYear.js" type="text/javascript"></script>
        <!--DELETE TABLE-->
        <script src="jsEducImports/directorydelete.js" type="text/javascript"></script>
        <script src="jsImported/directoryChecker.js" type="text/javascript"></script>

        <style>
            table th{
                text-align:center;
            }
            table td{
                text-align:center;
            }
            .table td {
                vertical-align: bottom!important;
            }
            #map {
                height: 100%;
            }
            .controls {
                margin-top: 10px;
                border: 1px solid transparent;
                border-radius: 2px 0 0 2px;
                box-sizing: border-box;
                -moz-box-sizing: border-box;
                height: 32px;
                outline: none;
                box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
            }

            #pac-input {
                background-color: #fff;
                font-family: Roboto;
                font-size: 15px;
                font-weight: 300;
                margin-left: 12px;
                padding: 0 11px 0 13px;
                text-overflow: ellipsis;
                width: 300px;
            }

            #pac-input:focus {
                border-color: #4d90fe;
            }

            .pac-container {
                font-family: Roboto;
            }

            #type-selector {
                color: #fff;
                background-color: #4d90fe;
                padding: 5px 11px 0px 11px;
            }

            #type-selector label {
                font-family: Roboto;
                font-size: 13px;
                font-weight: 300;
            }
            #target {
                width: 345px;
            }

            .width20{
                width: 30%;
            }

            input[type='number']{
                background: transparent; border: none;
            }

            .search-border{
                border: solid;
                border-width: thin;
                border-color: #d2d6de;
                padding: 1%;
            }
        </style>
    </head>
    <body>

        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <section class="content">
                    <div class="row">


                        <ol class="breadcrumb" style='background: transparent; margin-left: 3%; font-size: 120%;'>
                            <li class="title">Education</li>
                            <li class="active title">Number of Teachers and Classrooms for Private Schools</li>
                        </ol>
                        
                        <div style=" margin: 0 auto; display:block; text-align: center">
                            <div class="form-inline">
                                <div class="form-group" style="background: transparent;">
                                    <input style="width:  400px" id="schoolNameSearch" type="text" class="form-control" placeholder="Search School Name" onkeypress="autoCompleteSchool()" />
                                    <button class="btn btn-default" onClick="setSchoolData()"><span class="glyphicon glyphicon-search"></span></button>
                                    <button class="btn btn-default" id="viewAll" disabled="disabled" onclick="viewAll()">View All</button>
                                    <!-- Trigger the modal with a button -->
                                    <button class="btn btn-primary" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-plus"></span> Add New</button>
                                </div>
                                <form id="submitAll" action="UpdateSchoolDirectory" method="post">
                                    <input type="hidden" name="redirect" value="submitAll"/>
                                    <input type="hidden" name="censusYear" id= "year"/>
                                    <input type="hidden" name="uploadedBy" value="<%=user.getUserID()%>"/>
                                    <input type="hidden" id= "classification" name="classification" value="private"/>
                                    <input class="btn btn-flat btn-primary button-submit" id="btnsubmit" type="Submit" value="Submit"/>
                                </form>
                            </div>

                        </div>


                        <!--MODAL-->
                        <div id="showModalWarning" class="modal fade" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Warning!</h4>
                                    </div>
                                    <div class="modal-body" id="modal-body">

                                    </div>
                                    <div class="modal-footer" id="footer">

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--END MODAL-->
                        <br/>

                        <%                            ArrayList<directorySchool> directorySchool = (ArrayList<directorySchool>) request.getAttribute("directory");%>

                        <div class="DT" id="SchoolDirectory">                                <input type="hidden" id="censusYear" value="<%=directorySchool.get(0).getCensusYear()%>"/>

                            <table id="approved" class="table table-bordered" role="grid" aria-describedby="incomplete_info">
                                <%
                                    for (int i = 0; i < directorySchool.size(); i++) {
                                %>

                                <tbody>
                                    <tr style = "background-color: #454545; color: #fff" >
                                        <th style="vertical-align: bottom; text-align: left;" >Name of School</th>
                                        <td class="nr" colspan = "8" style="border-right: none; text-align: left;"> <%=directorySchool.get(i).getSchoolName()%></td>
                                        <td style="border-left: none; text-align: right">
                                            <button id="updateDirectory" class="btn btn-success btn-sm" ><span class="fa fa-edit"></span> Edit</button>
                                            <button id="invalidDirectory"  class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span> Remove</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th colspan="6">Teachers</th>
                                        <th colspan="2">Classrooms</th>
                                        <th colspan="2">Seats</th>
                                    </tr>
                                    <tr>
                                        <th colspan="3">Kinder</th>
                                        <th colspan="3">Elementary</th>
                                        <th rowspan="2" style="vertical-align: bottom;
                                            border-left: solid;
                                            border-width: thin;
                                            border-color: #d2d6de;
                                            padding: 1%;">Kinder</th>
                                        <th rowspan="2" style="vertical-align: bottom; ">Elementary</th>
                                        <th rowspan="2" style="vertical-align: bottom; ">Kinder</th>
                                        <th rowspan="2" style="vertical-align: bottom; ">Elementary</th>

                                    </tr>
                                    <tr>
                                        <th>Male</th>
                                        <th>Female</th>
                                        <th>Total</th>
                                        <th>Male</th>
                                        <th>Female</th>
                                        <th>Total</th>
                                    </tr>
                                    <tr>

                                        <!--Teachers-->
                                        <% int totalKinder = 0;
                                            int totalElem = 0;
                                            for (int y = 0; y < directorySchool.get(i).getTeacher().size(); y++) {%>
                                        <% if (directorySchool.get(i).getTeacher().get(y).getGradeLevel().equals("Kinder")) {%>
                                        <td class="KtMale"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getTeacher().get(y).getMaleCount())%></td>
                                        <td class="KtFemale"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getTeacher().get(y).getFemaleCount())%></td>
                                        <td class="KTotal"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getTeacher().get(y).getMaleCount() + directorySchool.get(i).getTeacher().get(y).getFemaleCount())%></td>
                                        <% totalKinder = directorySchool.get(i).getTeacher().get(y).getMaleCount() + directorySchool.get(i).getTeacher().get(y).getFemaleCount();
                                            }
                                            if (directorySchool.get(i).getTeacher().get(y).getGradeLevel().equals("Elementary")) {%>
                                        <td class="EtMale"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getTeacher().get(y).getMaleCount())%></td>
                                        <td class="EtFemale"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getTeacher().get(y).getFemaleCount())%></td>
                                        <td class="ETotal"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getTeacher().get(y).getMaleCount() + directorySchool.get(i).getTeacher().get(y).getFemaleCount())%></td>
                                        <% totalElem = directorySchool.get(i).getTeacher().get(y).getMaleCount() + directorySchool.get(i).getTeacher().get(y).getFemaleCount();
                                                }

                                            }%>

                                        <!--Classrooms-->
                                        <% int totalClassroom = 0;
                                            for (int y = 0; y < directorySchool.get(i).getElemClassrooms().size(); y++) {
                                                if (directorySchool.get(i).getElemClassrooms().get(y).getGradeLevel().equals("Kinder")) {
                                        %>
                                        <td class="KClassroom"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getElemClassrooms().get(y).getClassroomCount())%></td>
                                        <% totalClassroom += directorySchool.get(i).getElemClassrooms().get(y).getClassroomCount();  %>
                                        <% }
                                            }
                                            for (int y = 0; y < directorySchool.get(i).getElemClassrooms().size(); y++) {
                                                if (directorySchool.get(i).getElemClassrooms().get(y).getGradeLevel().equals("Elementary")) {%>
                                        <td class="EClassroom"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getElemClassrooms().get(y).getClassroomCount())%></td>
                                        <% totalClassroom += directorySchool.get(i).getElemClassrooms().get(y).getClassroomCount();  %>
                                        <% } %>
                                        <% }%>

                                        <!--<td></td>-->

                                        <!--Seats-->
                                        <% int totalSeats = 0;
                                            for (int y = 0; y < directorySchool.get(i).getSeats().size(); y++) {
                                                if (directorySchool.get(i).getSeats().get(y).getGradeLevel().equals("Kinder")) {%>
                                        <td class="Kseats"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getSeats().get(y).getSeatCount())%></td>
                                        <% totalSeats += directorySchool.get(i).getSeats().get(y).getSeatCount();  %>
                                        <% }
                                            }
                                            for (int y = 0; y < directorySchool.get(i).getSeats().size(); y++) {
                                                if (directorySchool.get(i).getSeats().get(y).getGradeLevel().equals("Elementary")) {%>
                                        <td class="Eseats"><%= directorySchool.get(i).getFormatcount(directorySchool.get(i).getSeats().get(y).getSeatCount())%></td>
                                        <% totalSeats += directorySchool.get(i).getSeats().get(y).getSeatCount();  %>
                                        <% }
                                            }%>
                                        <!--<td></td>-->
                                    </tr>
                                    <tr>
                                        <th colspan="5">Total Teachers</th>
                                        <td><%= directorySchool.get(i).getFormatcount(totalKinder + totalElem)%></td>
                                        <th>Total Classrooms</th>
                                        <td><%= directorySchool.get(i).getFormatcount(totalClassroom)%> </td>
                                        <th>Total Seats</th>
                                        <td><%= directorySchool.get(i).getFormatcount(totalSeats)%></td>
                                    </tr>

                                </tbody>
                                <%
                                    }
                                %>

                            </table>
                        </div>

                        <input type="hidden" id= "page" value="schoolDirectory"/>

                        <div class="DT" id="dataHolder">

                        </div>
                    </div>
                </section>
            </div>
        </div>
        <script>
            function changeSum() {
                var totalPoints = 0;
                $('.add').each(function () {
                    totalPoints = parseInt($(this).val()) + totalPoints;
                });
                $('#totalTeachers').val(totalPoints);
            }
            function changeClassroom() {
                var totalPoints = 0;
                $('.classroom').each(function () {
                    totalPoints = parseInt($(this).val()) + totalPoints;
                });
                $('#totalClassrooms').val(totalPoints);
            }
            function changeSeats() {
                var totalPoints = 0;
                $('.seats').each(function () {
                    totalPoints = parseInt($(this).val()) + totalPoints;
                });
                $('#totalSeats').val(totalPoints);
            }
        </script>
        <script>
            // This example adds a search box to a map, using the Google Place Autocomplete
            // feature. People can enter geographical searches. The search box will return a
            // pick list containing a mix of places and predicted search terms.

            // This example requires the Places library. Include the libraries=places
            // parameter when you first load the API. For example:
            // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">
            var map;
            function initMap() {
                map = new google.maps.Map(document.getElementById('map'), {
                    center: {lat: -33.8688, lng: 151.2195},
                    zoom: 10
                });
                var input = /** @type {!HTMLInputElement} */(
                        document.getElementById('pac-input'));

                map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
                var options = {
                    componentRestrictions: {
                        country: 'PH'}
                };

                var autocomplete = new google.maps.places.Autocomplete(input, options);
                autocomplete.bindTo('bounds', map);

                var infowindow = new google.maps.InfoWindow();
                var marker = new google.maps.Marker({
                    map: map,
                    anchorPoint: new google.maps.Point(0, -29)
                });

                autocomplete.addListener('place_changed', function () {
                    infowindow.close();
                    marker.setVisible(false);
                    var place = autocomplete.getPlace();
                    if (!place.geometry) {
                        window.alert("Autocomplete's returned place contains no geometry");
                        return;
                    }

                    // If the place has a geometry, then present it on a map.
                    if (place.geometry.viewport) {
                        map.fitBounds(place.geometry.viewport);
                    } else {
                        map.setCenter(place.geometry.location);
                        map.setZoom(17);  // Why 17? Because it looks good.
                    }
                    marker.setIcon(/** @type {google.maps.Icon} */({
                        url: place.icon,
                        size: new google.maps.Size(71, 71),
                        origin: new google.maps.Point(0, 0),
                        anchor: new google.maps.Point(17, 34),
                        scaledSize: new google.maps.Size(35, 35)
                    }));
                    marker.setPosition(place.geometry.location);
                    marker.setVisible(true);

                    var address = '';
                    if (place.address_components) {
                        address = [
                            (place.address_components[0] && place.address_components[0].short_name || ''),
                            (place.address_components[1] && place.address_components[1].short_name || ''),
                            (place.address_components[2] && place.address_components[2].short_name || '')
                        ].join(' ');
                    }

                    infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
                    var latitude = place.geometry.location.lat();
                    var longitude = place.geometry.location.lng();
                    console.log("latitude" + latitude);
                    console.log("longitude" + longitude);

                    $('#inputAddress').val(address);
                    $('#lat').val(latitude);
                    $('#long').val(longitude);
                    $('#classification').val("Private");
                    infowindow.open(map, marker);
                });
            }
        </script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyADR3ipXgTLZd7uIcl3NBUujxF3kKp9rFk&libraries=places&callback=initMap"
        async defer></script>
        <script>
            $(document).ready(function () {
                $('#myModal').on('shown', function () {
                    google.maps.event.trigger(map, "resize");
                });
            });
            function viewAll() {
                $('#dataSchool').remove();

                document.getElementById('SchoolDirectory').style.display = "block";
                $('#viewAll').attr('disabled', 'disabled');
            }
        </script>
    </body>
</html>
