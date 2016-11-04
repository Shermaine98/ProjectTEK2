<%--
    Document   : home_others
    Created on : 07 15, 16, 10:17:46 PM
    Author     : Geraldine
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="levelOfAccess.jsp"%>
<%@include file="JSPViewModal/notifcationModal.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project TEK | Home </title>
        <link href="cssImported/jquery.tagit.css" rel="stylesheet" type="text/css"/>
        <link href="cssImported/tagit.ui-zendesk.css" rel="stylesheet" type="text/css"/>
        <script src="jsForums/tag-it.js" type="text/javascript"></script>
        <script src="jsForums/forums.js" type="text/javascript"></script>
        <script src="jsImported/list.min.js" type="text/javascript"></script>
        <script src="jsImported/jquery.bootstrap.wizard.min.js" type="text/javascript"></script>
        <script src="jsImported/jquery.autocomplete.js" type="text/javascript"></script>
        <script src="jsReport/searchYearPublish.js" type="text/javascript"></script>
        <link href="cssImported/search.css" rel="stylesheet" type="text/css"/>


        <style>
            tr{
                height: 50px;
            }
            .wrapper{
                padding: 25px 25px;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <!-- Content Wrapper. Contains page content -->

            <!--MODAL-->

            <div class="col-md-12" style="margin-bottom: 2%;">
                <div class="box-body">
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1" class=""></li>
                            <!--<li data-target="#carousel-example-generic" data-slide-to="2" class=""></li>-->
                        </ol>
                        <div class="carousel-inner">
                            <div class="item active">
                                <img src="img/Caloocan1.jpg" alt="First slide" style="width:100%;height: 300px;object-fit: cover">

                            </div>
                            <div class="item">
                                <img src="img/Caloocan2.jpg" alt="Second slide" style="width:100%;height: 300px;object-fit: cover">
                            </div>
                            <!--                                        <div class="item">
                                                                        <img src="img/Caloocan3.jpg" alt="Third slide" style="width:100%;height: 200px;object-fit: cover">
                                                                    </div>-->
                        </div>
                        <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                            <span class="fa fa-angle-left"></span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                            <span class="fa fa-angle-right"></span>
                        </a>
                    </div>
                </div>
                <!--</div>-->
            </div>
            <div id="forumModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="box box-solid" style="margin-top: 2%;">
                        <div class="box-header with-border">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h3 class="box-title">Create New Topic</h3>
                        </div>
                        <div class="box-body">
                            <input id="forumTitle" placeholder="Topic Title" class="form-control" style="width:100%; resize: none; margin-bottom: 2%;" />
                            <textarea id="forumBody" placeholder="Body" class="form-control" style="width:100%; resize: none; height: 150px; margin-bottom: 2%;"></textarea>
                            <input class="tagit-label" style="width:100%; resize: none; margin-bottom: 2%;" placeholder="Tags" id="tagInput" />
                            <button type="submit" data-dismiss="modal" onclick="submitNewForum()" class="btn btn-success pull-right" >Save</button>
                        </div>
                    </div>
                </div>
            </div>

            <!--END MODAL-->
            <div class="col-md-7" style="margin-left: 1%; padding-right: 0;">
                <div class="box box-success">
                    <div class="box-header with-border">
                        <h3 class="box-title" align="center">ANNOUNCEMENT!</h3>
                    </div>
                    <div class="box-body">
                        <h4 style="font-weight: bold;">CALOOCAN BAGS #1 SPOT IN INVESTMENTS AND UTILITIES, 2ND HIGHEST LOCAL ECONOMIC GROWTH IN THE PHIL.</h4>
                        <p>The National Competitiveness Council - Philippines (NCC-P) has released specific rankings of provinces, cities and municipalities in its competitiveness studies of 81 provinces, 145 cities and 1,489 municipalities, with Caloocan City emerging as the top city in investment promotions and availability of utilities.</p>
                    </div>
                </div>
                <div class="box box-solid">
                    <div class="box-header with-border">
                        <h3 class="box-title" align="center">Published Reports</h3>
                    </div>
                    <div style=" margin: 0 auto; display:block; text-align: center">
                        <div class="form-inline">
                            <form action="ServletAccess" method="post">
                                <input name="redirect" type="hidden" value="ReportSearchForum" />
                                <div class="form-group">
                                    <select id="category" name="sectorReport" class="form-control" onchange="updateReport()" style="width:180px;">
                                        <option disabled selected>Choose Sector</option>
                                        <option value="Education">Education</option>
                                        <option value="Demographics">Demographics</option>
                                        <option value="Health">Health</option>
                                    </select>
                                    <select id="form_name" name="reportTitle" class="form-control" disabled onchange="updateYear()" style="width:280px;">
                                        <option disabled selected>Choose Report</option>
                                    </select>
                                    <input style="width: 110px;" type="text" class="form-control" onkeyup="updateButton()" disabled name="yearReport"  id="searchCensusYear" placeholder="Census Year" />
                                    <button disabled id="button" type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-5 pull-right" style="margin-right:1%; width: 38%; padding-left: 1%;">
                <div class="box box-solid">
                    <div class="box-header with-border">
                        <h3 class="box-title">Hot Topics</h3>
                    </div>
                    <div class="box-body">
                        <div id="hotTopicDiv">
                           
                        </div>
                    </div>
                </div>
                <div class="box box-solid">
                    <div class="box-header with-border">
                        <h3 class="box-title">All Topics</h3>
                        <button type="button" class="btn btn-flat btn-social btn-vk btn-sm pull-right" data-toggle="modal" data-target="#forumModal">New Topic <i class="glyphicon glyphicon-plus"></i></button>
                    </div>
                    <div class="box-body" id="searchDiv" >
                        <input style="margin-bottom: 2%;" class="search form-control" placeholder="Search by tags or by topic" />
                        <div id="forumDiv">
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <script>
            function updateReport() {
                var conceptName = $('#category').find(":selected").text();
                if (conceptName === "Demographics") {
                    $('#form_name')
                            .find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Report</option>')
                            .append('<option value="Matrix">Demographics Analysis Matrix</option>')
                            .append('<option value="Analysis">Demographics Analysis</option>')
                            .append('<option value="Integrated">Demographics Integrated Analysis</option>');

                } else if (conceptName === "Education") {
                    $('#form_name')
                            .find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Report</option>')
                            .append('<option value="Matrix">Education Analysis Matrix</option>')
                            .append('<option value="Analysis">Education Analysis</option>')
                            .append('<option value="Integrated">Education Integrated Analysis</option>');

                } else if (conceptName === "Health") {
                    $('#form_name')
                            .find('option')
                            .remove()
                            .end()
                            .append('<option disabled selected>Choose Report</option>')
                            .append('<option value="Matrix">Health Analysis Matrix</option>')
                            .append('<option value="Analysis">Health Analysis</option>')
                            .append('<option value="Integrated">Health Integrated Analysis</option>');
                }
                $('#form_name').removeAttr('disabled');
            }

            function updateYear() {
                var conceptName = $('#form_name').find(":selected").val();
                if (conceptName == "Matrix") {
                    $('#searchCensusYear').keypress(searchMatrix());
                    $('#searchCensusYear').removeAttr('disabled');

                } else if (conceptName == "Analysis") {
                    $('#searchCensusYear').keypress(searchAnalysis());
                    $('#searchCensusYear').removeAttr('disabled');

                } else if (conceptName == "Integrated") {
                    $('#searchCensusYear').keypress(searchIntegrated());
                    $('#searchCensusYear').removeAttr('disabled');

                }
            }
            function getData() {
                year = $('#searchCensusYear').val();
                var conceptName = $('#form_name').find(":selected").val();
                if (conceptName === "Matrix") {
                    document.getElementById('integrateData').style.display = "none";
                    document.getElementById('contentHere').style.display = "block";
                    $('#submitBtn').click(setMatrix());
                } else if (conceptName === "Analysis") {
                    document.getElementById('integrateData').style.display = "none";
                    document.getElementById('contentHere').style.display = "block";
                    $('#submitBtn').click(setAnalysis());
                } else if (conceptName === "Integrated") {
                    document.getElementById('contentHere').style.display = "none";
                    document.getElementById('integrateData').style.display = "block";

                    $('#submitBtn').click(setIntegrated());
                }
            }

            function updateButton() {
                if ($('#searchCensusYear').val()) {
                    $('#button').removeClass('btn-default');
                    $('#button').addClass('btn-primary');
                    $("#button").prop('disabled', false);
                } else if (!$('#searchCensusYear').val()) {
                    $('#button').removeClass('btn-primary');
                    $('#button').addClass('btn-default');
                    $("#button").prop('disabled', true);
                }
            }
        </script>
    </body>

</html>
