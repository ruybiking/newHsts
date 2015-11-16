/**
 * Created by QUYHKSE61160 on 11/4/15.
 */

var tableElement = document.getElementsByTagName("table")[0];
var rowElement = tableElement.rows[1];
var patientName = rowElement.cells[1].textContent;
var dateOfBirth = rowElement.cells[2].textContent;
var dateOfBirthArray = dateOfBirth.split("-");
var date = parseInt(dateOfBirthArray[0]);
var month = parseInt(dateOfBirthArray[1]);
var year = dateOfBirthArray[2];

var url = "http://118.69.135.22/synthesis/file?voiceType=female&text=mời bệnh nhân " + patientName + " sinh ngày " + date + " tháng " + month + " năm " + year + " vào khám";
var audio = new Audio(url);
audio.play();