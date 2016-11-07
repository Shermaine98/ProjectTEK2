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



                    </div>
                    <!--End of Body-->

                </section>
            </div> </div>

    </body>
</html>
