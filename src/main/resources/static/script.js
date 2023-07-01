function addDog(){
    window.location.href="/dogs/add-dogs";
}
function addOwner(){
    window.location.href="/owner/new-owner";
}
function filterByBreed(){
    const element = document.getElementById("breeds");
    const breed = element.options[element.selectedIndex].text;
    window.location.href="/dogs/filter/breed/" + breed;
}
function filterByOwner(){
    const element = document.getElementById("owner");
    const owner = element.options[element.selectedIndex].value;
    window.location.href="/dogs/filter/owner/" + owner;
}
function removeFilter(){
    window.location.href="/dogs";
}