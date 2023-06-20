const DAY_FIELDS = document.getElementsByClassName("day-field");
const MONTH_PANEL = document.getElementById("month");
const MONTHS = ["Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"];
const date = new Date();
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

    if (date.getDay()-1<=0){
        DAY_FIELDS[date.getDay()-1+7].innerHTML = 1;
        shifted = true;
        i++;
    }
    for (i;i < lastDayOfMonth.getDate();i++){
        if (shifted){
            DAY_FIELDS[date.getDay()+i-1+7].innerHTML = i + 1;
        }else {
            DAY_FIELDS[date.getDay()+i-1].innerHTML = i + 1;
        }
    }
}
function deleteCalendar(){
    for (let i = 0;i < DAY_FIELDS.length;i++){
        DAY_FIELDS[i].innerHTML = "";
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