/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Setting MIN & MAX DATES FOR DATEPICKERS
//Used for Minimum date is tomorrow (in index.html, until when researchers will have access)
var today = new Date().toISOString().split('T')[0];
var tomorrowLocal = new Date(new Date().getTime() + 24 * 60 * 60 * 1000 - new Date().getTimezoneOffset() * 60 * 1000).toISOString().substr(0, 10);
document.getElementById('employmentDate').setAttribute('max', today);
document.getElementById('employmentDate').setAttribute('value', today);

/**
 * Used for researchers (others) in register
 */
function yesnoCheck(that) {
    if (that.value == "Others") {
        document.getElementById("ifYes").style.display = "block";
        document.getElementById("ifNo").style.display = "none";
    } else if (that.value == "Others Divisions") {
        document.getElementById("ifNo").style.display = "block";
        document.getElementById("ifYes").style.display = "none";
        document.getElementById("SocialPosition").style.display = "none";
        document.getElementById("PhysicalPosition").style.display = "none";
         document.getElementById("instiPosition").style.display = "none";
    } else if (that.value == "Physical Development Planning Division") {
        document.getElementById("ifNo").style.display = "block";
        document.getElementById("PhysicalPosition").style.display = "block";
        document.getElementById("ifYes").style.display = "none";
        document.getElementById("SocialPosition").style.display = "none";
         document.getElementById("instiPosition").style.display = "none";
    } else if (that.value == "Social Development Planning Division") {
        document.getElementById("ifNo").style.display = "block";
        document.getElementById("SocialPosition").style.display = "block";
        document.getElementById("ifYes").style.display = "none";
        document.getElementById("PhysicalPosition").style.display = "none";
       document.getElementById("instiPosition").style.display = "none";
    } else if (that.value == "Institutional Development Planning Division") {
        document.getElementById("ifNo").style.display = "block";
         document.getElementById("instiPosition").style.display = "block";
        document.getElementById("ifYes").style.display = "none";
        document.getElementById("SocialPosition").style.display = "none";
        document.getElementById("PhysicalPosition").style.display = "none";
    }
}

/**
 * Date 12 yrs from now
 */
var tenYears = new Date();
tenYears.setFullYear(tenYears.getFullYear() - 12);
var dMonth = tenYears.getMonth() + 1;
var dYear = tenYears.getFullYear();
var dDay = tenYears.getDate();
var birthdate = tenYears.toISOString().substring(0, 10);

document.getElementById('Birthdate').setAttribute('max', birthdate);
document.getElementById('Birthdate').value = tenYears.toISOString().substring(0, 10);
