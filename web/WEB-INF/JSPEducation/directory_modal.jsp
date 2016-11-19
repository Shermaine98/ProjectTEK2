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
                    <form action="AddNewSchool" method="post">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Add New School</h4>
                        </div>
                        <div class="modal-body">
                            <p>Please input school details below:</p>

                            <div class="form-inline" style="margin-top:3%;">
                                <label class="width20">Name of School: </label> <input type="text" name="schoolName" required class="form-control" style="width: 65%" /><br/><br/>
                                <label class="width20">School ID: </label> <input type="text" name="schoolID" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control" style="width: 65%" /><br/><br/>
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
                                        <td><input name="KteacherMale" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control kinderT" value="0" onchange="changeKinder()" /></td>
                                        <td><input name="KteacherFemale" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control kinderT" value="0" onchange="changeKinder()" /></td>
                                        <td><input name="KteacherTotal" id="KteacherTotal" type="number" style="background:transparent;border:none;" class="form-control totalT" min="0" value="0" readonly /></td>
                                        <td><input name="EteacherMale" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control ElemT"  value="0" onchange="changeElem()" /></td>
                                        <td><input name="EteacherFemale" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control ElemT"  value="0" onchange="changeElem()" /></td>
                                        <td><input name="EteacherTotal" id="EteacherTotal" type="text" style="background:transparent;border:none;" class="form-control totalT"  value="0"  /></td>
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
                                        <td> <input name="KinderClassRoom" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control classroom"  value="0" onchange="changeClassroom()" /></td>
                                        <td><input name="ElemClassRoom"  class="form-control classroom" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  value="0" onchange="changeClassroom()" /></td>
                                        <td><input name="TotalClassrooms" type="text" class="form-control" style="background:transparent;border:none;" readonly value="0" id="totalClassrooms" /></td>
                                        <td><input name="KinderSeats" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control seats"  value="0" onchange="changeSeats()" /></td>
                                        <td><input name="ElemSeats" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control seats"  value="0" onchange="changeSeats()" /></td>
                                        <td><input name="TotalSeats" type="text" class="form-control" style="background:transparent;border:none;" readonly value="0" id="totalSeats" /></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>


                        <div class="form-inline" align="center">
                            <div class="form-group" style="background: transparent;">
                                <input class="form-control" name="lat" id="lat" type="hidden"  />
                                <input class="form-control" name="long" id="long" type="hidden"  />
                                <input class="form-control" name="classification" id="classification" type="hidden"  />
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button  class="btn btn-success" >Submit</button>
                        </div>
                    </form>
                </div>


            </div>
        </div>
        <!-- END ADD  NEW Modal -->

        <!--MODAL UPDATE-->
        <div id="UpdateModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <form action="UpdateSchoolDirectory" method="post">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Edit School Details</h4>
                        </div>
                        <div class="modal-body">
                            <p>Please input school details below:</p>

                            <div class="form-inline" style="margin-top:3%;">
                                <label class="width20">Name of School: </label> <input type="text" required id="schoolNameEdit" name="schoolName" class="form-control" style="width: 65%" readonly /><br/><br/>
                                <label class="width20">School ID: </label> <input type="text" id="SchoolIDEdit"  required onkeypress="return event.charCode >= 48 && event.charCode <= 57" readonly name="schoolID" class="form-control" style="width: 65%" /><br/><br/>
                            </div>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th colspan="6">Teachers</th>
                                    </tr>
                                    <tr>
                                        <th colspan="3">Elementary</th>
                                        <th colspan="3">Kinder</th>
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
                                        <td><input name="KteacherMale" id="EditKtM" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57" class="form-control kinderTE"  value="0" onchange="changeKinderE()" /></td>
                                        <td><input name="KteacherFemale" id="EditKtF" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control kinderTE"  value="0" onchange="changeKinderE()" /></td>
                                        <td><input name="KteacherTotal"  id="EKteacherTotal" type="text" style="background:transparent;border:none;" class="form-control totalT" value="0" readonly /></td>
                                        <td><input name="EteacherMale" id="EditElemTM" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control ElemTE"  value="0" onchange="changeElemE()" /></td>
                                        <td><input name="EteacherFemale" id="EditElemTF" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control ElemTE" value="0" onchange="changeElemE()" /></td>
                                        <td><input name="EteacherTotal" id="EditElteacherTotal" type="text" style="background:transparent;border:none;" class="form-control totalT"  value="0" readonly /></td>
                                    </tr>
                                    <tr>
                                        <th colspan="5" style="text-align:right; vertical-align:middle;">Total Teachers</th>
                                        <td><input type="text" readonly id="EtotalTeachers" class="form-control" style="background:transparent;border:none;" value="0" /></td>
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
                                        <td> <input name="cencusYear" id="cencusYearE" type="hidden"  /><input name="KinderClassRoom" id="EditeKinderClass" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57" class="form-control classroomE"  value="0" onchange="changeClassroomE()" /></td>
                                        <td><input name="ElemClassRoom" id="EditElemClass" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control classroomE"  value="0" onchange="changeClassroomE()" /></td>
                                        <td><input name="TotalClassrooms"  type="text" class="form-control" style="background:transparent;border:none;" readonly value="0" id="EtotalClassrooms" /></td>
                                        <td><input name="KinderSeats" id="EditKinderSeats" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control seatsE"  value="0" onchange="changeSeatsE()" /></td>
                                        <td><input name="ElemSeats" id="EditElemSeats" type="text" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"  class="form-control seatsE"  value="0" onchange="changeSeatsE()" /></td>
                                        <td><input name="TotalSeats" type="text" class="form-control" style="background:transparent;border:none;" readonly value="0" id="EtotalSeats" /></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="form-inline" align="center">
                            <div class="form-group" style="background: transparent;">
                                <input name="lat" class="form-control" id="lat" value="0" type="hidden"  />
                                <input name="long" class="form-control" id="long" value="0" type="hidden"/>
                                <input name="classification" id="classificationE" type="hidden" />
                                 <input name="redirect" value="updateData" type="hidden" />
                            </div>
                        </div>
                        <div class="modal-footer" id="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button  class="btn btn-success">Submit</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
        <!--END MODAL UPDATE-->
        <script>

            function changeKinder() {
                var totalPoints = 0;
                $('.kinderT').each(function () {
                    totalPoints = parseInt($(this).val()) + totalPoints;
                });
                $('#KteacherTotal').val(totalPoints);
                totlT();
            }
            function changeElem() {
                var totalPoints = 0;

                $('.ElemT').each(function () {
                    totalPoints = parseInt($(this).val()) + totalPoints;
                });

                $('#EteacherTotal').val(totalPoints);
                totlT();
            }
            function totlT() {
                var totalPoints = 0;
                $('.totalT').each(function () {
                    totalPoints = parseInt($(this).val()) + totalPoints;
                });
                $('#totalTeachers').val(totalPoints);
            }

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
        <!--UPDATE-->
        <script>

            function changeKinderE() {
                var totalPoints = 0;
                $('.kinderTE').each(function () {
                    totalPoints = parseInt($(this).val()) + totalPoints;
                });
                $('#EKteacherTotal').val(totalPoints);
                totlTE();
            }
            function changeElemE() {
                var totalPoints = 0;

                $('.ElemTE').each(function () {
                    totalPoints = parseInt($(this).val()) + totalPoints;
                });

                $('#EditElteacherTotal').val(totalPoints);
                totlTE();
            }
            function totlTE() {
                var totalPoints = 0;
                $('.totalTE').each(function () {
                    totalPoints = parseInt($(this).val()) + totalPoints;
                });
                $('#EtotalTeachers').val(totalPoints);
            }

            function changeClassroomE() {
                var totalPoints = 0;
                $('.classroomE').each(function () {
                    totalPoints = parseInt($(this).val()) + totalPoints;
                });
                $('#EtotalClassrooms').val(totalPoints);
            }
            function changeSeatsE() {
                var totalPoints = 0;
                $('.seatsE').each(function () {
                    totalPoints = parseInt($(this).val()) + totalPoints;
                });
                $('#EtotalSeats').val(totalPoints);
            }
        </script>
    </body>
</html>
