//global variables
var structure = ['region', 'depart', 'souspref', 'p03', 'p04'];
var level = 0; //root
var code = 0;

$(function () {
    setMenuActive("territory");
    loadSearchFilters(code); //root
});

function search() {

    var elementLevel = getLevel(this.id);

    if (elementLevel < level) { //going back
        deleteElements(elementLevel);
        level = elementLevel;
    }

    if (level < 2) { //if root
        code = $("#" + structure[0]).val();
    } else {
        code += "," + $("#" + structure[level - 1]).val();
    }
    
    loadSearchFilters(code);
}


function loadSearchFilters(ids) {

    var div = document.createElement('div'); //search filters div
    div.className = 'col-lg-2';
    var select = document.createElement('select');
    select.className = 'form-control';
    select.id = structure[level];
    select.onchange = search;

    $.ajax({
        url: ctx + "/rest/territory/filter/" + ids,
        type: "GET",
        success: function (data) {
            var option = document.createElement('option');
            option.value = -1;
            option.text = structure[level - 1];
            option.selected = "selected";
            select.append(option);

            $.each(data, function (index) {
                console.log(data[index][0] + " " + data[index][1]);
                var option = document.createElement('option');
                option.value = data[index][1];
                option.text = data[index][0];
                select.append(option);
            });
            div.append(select);
            $("#search-filters").append(div);
        },
        error: function () {
            alert('Error loading data');
        }
    });
    level++;
}

function getLevel(elementName) {
    for (var i = 0; i < structure.length; i++) {
        if (structure[i] === elementName)
            return i + 1;
    }
    return -1;
}

function deleteElements(nextElement) {
    for (var i = nextElement; i < structure.length; i++) {
        var element = document.getElementById(structure[i]);
        if (element !== null) {
            element.parentNode.remove(element);
        }
    }
}