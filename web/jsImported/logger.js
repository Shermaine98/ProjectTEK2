/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

$.log = function(message){
  var $logger = $("#logger");
  $logger.html($logger.html() + "\n * " + message );
}
