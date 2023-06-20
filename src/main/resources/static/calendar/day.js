const DAY_ELEMENT = document.getElementById('day');
const MONTH_ELEMENT = document.getElementById('month');
const YEAR_ELEMENT = document.getElementById('year');
const MONTHS = ["Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"];
const YEAR = YEAR_ELEMENT.innerHTML;
const MONTH = MONTH_ELEMENT.innerHTML;
const DAY = DAY_ELEMENT.innerHTML;

MONTH_ELEMENT.innerHTML = MONTHS[MONTH_ELEMENT.innerHTML];

function addEvent(){
    window.location.href = `/calendar/${YEAR}/${MONTH}/${DAY}/add-event`;
}