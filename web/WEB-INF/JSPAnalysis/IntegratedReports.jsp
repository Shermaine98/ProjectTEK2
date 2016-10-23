<%--
    Document   : StandardManipulation
    Created on : 07 19, 16, 12:36:46 AM
    Author     : Geraldine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../levelOfAccess.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/data.js"></script>
        <script src="Highcharts/modules/drilldown.js"></script>
        <script src="Highcharts/modules/exporting.js"></script>
        <script src="jsAnalysisImports/synchronizedCharts.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Highcharts/highmaps.js" type="text/javascript"></script>
        <script src="Highcharts/modules/map.js" type="text/javascript"></script>
        <script src="Highcharts/modules/data.js" type="text/javascript"></script>
        <script src="jsAnalysisImports/jsHighMaps.js" type="text/javascript"></script>
        <title>Integrated Analytics</title>

        <style>
            .textarea{
                margin: 0 auto;
                width: 80%;
                vertical-align: middle;
            }
            textarea{
                resize: none;
                width: 100%;
            }
            #map {
                height: 500px;
                min-width: 310px;
                max-width: 800px;
                margin: 0 auto;
            }
            .loading {
                margin-top: 10em;
                text-align: center;
                color: gray;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <div class="content-wrapper">
                <section class="content-header">
                    <h1><img src="AdminLTE/dist/img/analysis.png" style="width: 3%;" alt=""/> Analysis</h1>
                </section>

                <ol class="breadcrumb" style='background: transparent; margin-left: 3%; font-size: 120%;'>
                    <li class="title">Analysis</li>
                    <li class="active title">Integrated Analytics</li>
                </ol>
                <section class="content">
                    <div class="row">

                        <div class="col-md-6">
                            <div class="box box-solid">
                                <div class="box-header with-border" style="background: #a1bce1; color: #FFF">
                                    <h4 class="box-title">Filter By Year</h4>
                                </div>
                                <div class="box-body">
                                    <div id="years" style="height: 140px;"> <!--- overflow-y: scroll; -->
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="box box-solid">
                                <div class="box-header with-border" style="background: #a1bce1; color: #FFF">
                                    <h4 class="box-title">Filter By District</h4>
                                </div>
                                <div class="box-body">
                                    <div id="districts" style=" height: 140px;"> <!--- overflow-y: scroll; -->
                                    </div>
                                </div>
                            </div>
                        </div>



                        <div class="col-md-12">
                            <div class="box box-solid">
                                <div class="box-body">

                                    <div id="container" style="min-width: 310px; height: 300px; margin: 0 auto"></div>
                                    <div id="container2" style="min-width: 310px; height: 300px; margin: 0 auto"></div>
                                    <div id="container3" style="min-width: 310px; height: 300px; margin: 0 auto"></div>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>


                        <form id="reportIntegrated" action="SavedReportIntegration" method="post">
                            <div class="col-md-12">
                                <div class="box box-solid">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Create Report</h3>
                                    </div>
                                    <!-- /.box-header -->
                                    <div class="box-body">
                                        <div id="trial"></div>
                                        <textarea class="form-control" name="text" rows="10"></textarea>
                                    </div>
                                    <!-- /.box-body -->
                                </div>
                                <!-- /.box -->
                            </div>

                            <div align="center">
                                <input type="hidden" class="draftID" name="isDraft" id="reportDraftIntegration" />
                                <button class="btn btn-primary button-submit" type="button" style="margin-right: 1%;" onClick="setDraft(this)">Save</button>
                            </div>
                        </form>


                    </div>
                    <div id="publishWarning" class="modal fade" role="dialog">
                        <div class="modal-dialog" style="padding: 0; margin-left: 30%; margin-top: 10%;">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header" style="background: #f39c12;">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title" style="color: #FFF;">Warning!</h4>
                                </div>
                                <div class="modal-body" align="center">
                                    <p>You will no longer be able to edit this report once you submit it.
                                        Are you sure you want to proceed?</p>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-primary button-submit"  onClick="setPublish()" value="Submit"/>Submit</button>
                                    <button class="btn btn-default" data-dismiss="modal" type="button">Cancel</button>
                                </div>
                            </div>

                        </div>
                    </div>
                    <!--End of Body-->

                </section>
            </div> </div>
        <script>

            function onClickModal(modal) {
                var x = $(modal).parents("form").find(".draftID");
                x.val("false");
                form = $(modal).parents('form:first');
                console.log(form);
                $("#publishWarning").modal("show");
            }

            function setPublish() {
                form.submit();
            }

            function setDraft(draft) {
                var x = $(draft).parents("form").find(".draftID");
                x.val("true");
                var draftForm = $(draft).parents('form:first');
                console.log(draftForm);
                draftForm.submit();
            }

        </script>
    </body>
</html>
