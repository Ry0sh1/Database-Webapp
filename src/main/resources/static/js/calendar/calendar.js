const DAY_FIELDS = document.getElementsByClassName("day-field");
const MONTH_PANEL = document.getElementById("month");
const MONTHS = ["Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"];
const date = new Date();

const DAY_WITH_EVENTS_DAY = document.getElementsByClassName("day");
const DAY_WITH_EVENTS_MONTH = document.getElementsByClassName("month");
const DAY_WITH_EVENTS_YEAR = document.getElementsByClassName("year");

makeCalendar();

function changeMonth(n) {
    date.setMonth(date.getMonth() + n);
    makeCalendar();
}
function makeCalendar(){
    deleteCalendar();
    let lastDayOfMonth = new Date(date.getFullYear(), date.getMonth()+1,0);

    date.setDate(1);

    let shifted = false;

    MONTH_PANEL.innerHTML = MONTHS[date.getMonth()] + ' ' + date.getFullYear();

    let i = 0;

    //If first day is on Sunday
    if (date.getDay()-1<=0){
        DAY_FIELDS[date.getDay()-1+7].innerHTML = 1;
        if (hasEvent(1)){
            DAY_FIELDS[date.getDay()-1+7].classList.add("event");
        }
        shifted = true;
        i++;
    }
    for (i;i < lastDayOfMonth.getDate();i++){
        let DAY;
        if (shifted){
            DAY = DAY_FIELDS[date.getDay()+i-1+7];
            DAY.innerHTML = i + 1;
        }else {
            DAY = DAY_FIELDS[date.getDay()+i-1];
            DAY.innerHTML = i + 1;
        }
        if (hasEvent(i+1)){
            DAY.classList.add("event");
        }
    }
}
function deleteCalendar(){
    for (let i = 0;i < DAY_FIELDS.length;i++){
        DAY_FIELDS[i].innerHTML = "";
        DAY_FIELDS[i].classList.remove("event");
    }
}
function dayClick(target){
    if (target.innerHTML === ''){
        //DO NOTHING
    }else {
        let day = target.innerHTML;
        let month =date.getMonth();
        let year = date.getFullYear();
        window.location.href = `/calendar/${year}/${month}/${day}`;
    }
}
function hasEvent(pDate){
    const year = date.getFullYear();
    const month = date.getMonth();

    for (let i = 0;i < DAY_WITH_EVENTS_DAY.length;i++){
        if (`${year}` === DAY_WITH_EVENTS_YEAR[i].innerHTML &&
            `${month}` === DAY_WITH_EVENTS_MONTH[i].innerHTML &&
            `${pDate}` === DAY_WITH_EVENTS_DAY[i].innerHTML){
            return true;
        }
    }
    return false;
}