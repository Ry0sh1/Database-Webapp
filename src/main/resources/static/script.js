function addDog(){
    window.location.href="/add-dogs";
}
function addOwner(){
    window.location.href="/new-owner";
}
function filterByBreed(){
    const breeds = document.getElementById("breeds");
    const breed = breeds.options[breeds.selectedIndex].text;
    window.location.href="/dogs/filter/" + breed    ;
}
function removeFilter(){
    window.location.href="/dogs";
}