<%--
    Document   : directory_modal
    Created on : 07 16, 16, 11:10:04 PM
    Author     : Geraldine Atayan, Gian Roxas, Shermaine Sy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>

        <!-- ADD  NEW Modal -->
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <form action="" method="post">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Add New School</h4>
                        </div>
                        <div class="modal-body">
                            <p>Please input school details below:</p>

                            <div class="form-inline" style="margin-top:3%;">
                                <label class="width20">Name of School: </label> <input type="text" name="schoolName" class="form-control" style="width: 65%" /><br/><br/>
                                <label class="width20">School ID: </label> <input type="text" name="schoolID" class="form-control" style="width: 65%" /><br/><br/>
                                <label class="width20">Address: </label> <input type="text" class="form-control" name="Address" id="inputAddress" style="width: 65%" /><br/><br/>
                            </div>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th colspan="6">Teachers</th>
                                    </tr>
                                    <tr>
                                        <th colspan="3">Kinder</th>
                                        <th colspan="3">Elementary</th>
                                    </tr>
                                    <tr>
                                        <th>Male</th>
                                        <th>Female</th>
                                        <th>Total</th>
                                        <th>Male</th>
                                        <th>Female</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><input name="KteacherMale" type="number" class="form-control add" min="0" value="0" onchange="changeSum()" /></td>
                                        <td><input name="KteacherFemale" type="number" class="form-control add" min="0" value="0" onchange="changeSum()" /></td>
                                        <td><input name="KteacherTotal" type="number" class="form-control add" min="0" value="0" onchange="changeSum()" /></td>
                                        <td><input name="EteacherMale" type="number" class="form-control add" min="0" value="0" onchange="changeSum()" /></td>
                                        <td><input name="EteacherFemale" type="number" class="form-control add" min="0" value="0" onchange="changeSum()" /></td>
                                        <td><input name="EteacherTotal" type="number" class="form-control add" min="0" value="0" onchange="changeSum()" /></td>
                                    </tr>
                                    <tr>
                                        <th colspan="5" style="text-align:right;">Total Teachers</th>
                                        <td><input type="text" readonly id="totalTeachers" class="form-control" style="background:transparent;border:none;" value="0" /></td>
                                    </tr>
                                </tbody>
                            </table>
                            <br/>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th colspan="3">Classrooms</th>
                                        <th colspan="3">Seats</th>
                                    </tr>
                                    <tr>
                                        <th>Kinder</th>
                                        <th>Elementary</th>
                                        <th>Total Classrooms</th>
                                        <th>Kinder</th>
                                        <th>Elementary</th>
                                        <th>Total Seats</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><input name="KinderClassRoom" type="number" class="form-control classroom" min="0" value="0" onchange="changeClassroom()" /></td>
                                        <td><input name="ElemClassRoom" type="number" class="form-control classroom" min="0" value="0" onchange="changeClassroom()" /></td>
                                        <td><input name="TotalClassrooms" type="text" class="form-control" style="background:transparent;border:none;" readonly value="0" id="totalClassrooms" /></td>
                                        <td><input name="KinderSeats" type="number" class="form-control seats" min="0" value="0" onchange="changeSeats()" /></td>
                                        <td><input name="ElemSeats" type="number" class="form-control seats" min="0" value="0" onchange="changeSeats()" /></td>
                                        <td><input name="TotalSeats" type="text" class="form-control" style="background:transparent;border:none;" readonly value="0" id="totalSeats" /></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>


                        <div class="form-inline" align="center">
                            <div class="form-group" style="background: transparent;">
                                <input class="form-control" name="lat" id="lat" type="hidden"  />
                                <input class="form-control" name="long" id="long" type="hidden"  />
                                <input class="form-control" name="classification" id="classification" type="text" placeholder="Classification" />
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-success" data-dismiss="modal">Submit</button>
                        </div>
                    </form>
                </div>


            </div>
        </div>
        <!-- END ADD  NEW Modal -->

        <!--MODAL UPDATE-->
        <div id="UpdateModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <form action="" method="post">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Edit School Details</h4>
                        </div>
                        <div class="modal-body">
                            <p>Please input school details below:</p>

                            <div class="form-inline" style="margin-top:3%;">
                                <label class="width20">Name of School: </label> <input type="text" name="schoolName" class="form-control" style="width: 65%" /><br/><br/>
                                <label class="width20">School ID: </label> <input type="text" name="schoolID" class="form-control" style="width: 65%" /><br/><br/>
                                <label class="width20">Address: </label> <input type="text" class="form-control" name="Address" id="inputAddress" style="width: 65%" /><br/><br/>
                            </div>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th colspan="6">Teachers</th>
                                    </tr>
                                    <tr>
                                        <th colspan="3">Kinder</th>
                                        <th colspan="3">Elementary</th>
                                    </tr>
                                    <tr>
                                        <th>Male</th>
                                        <th>Female</th>
                                        <th>Total</th>
                                        <th>Male</th>
                                        <th>Female</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><input name="KteacherMale" type="number" class="form-control kinderT" min="0" value="0" onchange="changeKinder()" /></td>
                                        <td><input name="KteacherFemale" type="number" class="form-control kinderT" min="0" value="0" onchange="changeKinder()" /></td>
                                        <td><input name="KteacherTotal" type="number" style="background:transparent;border:none;" class="form-control totalT" min="0" value="0" readonly /></td>
                                        <td><input name="EteacherMale" type="number" class="form-control ElemT" min="0" value="0" onchange="changeElem()" /></td>
                                        <td><input name="EteacherFemale" type="number" class="form-control ElemT" min="0" value="0" onchange="changeElem()" /></td>
                                        <td><input name="EteacherTotal" type="number" style="background:transparent;border:none;" class="form-control totalT" min="0" value="0" readonly /></td>
                                    </tr>
                                    <tr>
                                        <th colspan="5" style="text-align:right; vertical-align:middle;">Total Teachers</th>
                                        <td><input type="text" readonly id="totalTeachers" class="form-control" style="background:transparent;border:none;" value="0" /></td>
                                    </tr>
                                </tbody>
                            </table>
                            <br/>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th colspan="3">Classrooms</th>
                                        <th colspan="3">Seats</th>
                                    </tr>
                                    <tr>
                                        <th>Kinder</th>
                                        <th>Elementary</th>
                                        <th>Total Classrooms</th>
                                        <th>Kinder</th>
                                        <th>Elementary</th>
                                        <th>Total Seats</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><input name="KinderClassRoom" type="number" class="form-control classroom" min="0" value="0" onchange="changeClassroom()" /></td>
                                        <td><input name="ElemClassRoom" type="number" class="form-control classroom" min="0" value="0" onchange="changeClassroom()" /></td>
                                        <td><input name="TotalClassrooms" type="text" class="form-control" style="background:transparent;border:none;" readonly value="0" id="totalClassrooms" /></td>
                                        <td><input name="KinderSeats" type="number" class="form-control seats" min="0" value="0" onchange="changeSeats()" /></td>
                                        <td><input name="ElemSeats" type="number" class="form-control seats" min="0" value="0" onchange="changeSeats()" /></td>
                                        <td><input name="TotalSeats" type="text" class="form-control" style="background:transparent;border:none;" readonly value="0" id="totalSeats" /></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="form-inline" align="center">
                            <div class="form-group" style="background: transparent;">
                                <input name="lat" class="form-control" id="lat" value="0" type="hidden"  />
                                <input name="long" class="form-control" id="long" value="0" type="hidden"/>
                                <input name="classification" id="classification" type="hidden" />
                            </div>
                        </div>
                        <div class="modal-footer" id="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-success" data-dismiss="modal">Submit</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
        <!--END MODAL UPDATE-->
    </body>
</html>
