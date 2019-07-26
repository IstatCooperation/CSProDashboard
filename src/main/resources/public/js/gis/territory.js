//global variables
var structure = [];
var level = 1; //root
var codes = [0];
var territoryTable;

$(function () {
    setMenuActive("territory");
    getTerritoryStructure();
    loadSearchFilters(0); //root
});

function getTerritoryStructure() {

    $.ajax({
        url: ctx + "/rest/meta/territory/",
        type: "GET",
        async: false, //must wait until it completes
        success: function (data) {
            structure = data;
        },
        error: function () {
            alert('Error loading data');
        }
    });
}

function createFilters() {

    var elementLevel = getLevel(this.id);
    var elementVal = this.options[this.selectedIndex].value;

    getCodes(elementLevel, elementVal);

    if (elementLevel < level) { //going back
        deleteElements(elementLevel);
        level = elementLevel;
    }

    if (!isLastLevel(elementLevel) && elementVal > 0) {
        loadSearchFilters(elementLevel);
        level++; //going forward
    }
}

function loadSearchFilters(elementLvl) {

    var div = document.createElement('div'); //search filters div
    div.className = 'col-lg-2';
    var select = document.createElement('select');
    select.className = 'form-control';
    select.id = structure[elementLvl];
    select.onchange = createFilters;

    $.ajax({
        url: ctx + "/rest/territory/filter/" + getCodeString(),
        type: "GET",
        success: function (data) {
            var option = document.createElement('option'); //default option
            option.value = -1;
            option.text = structure[elementLvl];
            option.selected = true;
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
}

function loadTerritory() {

    $.ajax({
        url: ctx + "/rest/territory/" + getCodeString(),
        type: "GET",
        datatype: 'json',
        success: function (data) {
            var rows = [];
            jQuery.each(data, function (x) {
                rows[x] = [];
                for (var y = 0; y < structure.length; y++) {
                    rows[x][y] = data[x][y * 2]; //get description
                }
            });
            var columns = [];
            for (var i = 0; i < structure.length; i++) {
                columns[i] = {title: structure[i]};
            }
            populateTable(rows, columns);
            $("#territory-table-row").show();
        },
        error: function () {
            alert('Error loading data');
        }
    });
}


function clearFilters() {
    deleteElements(1);
    level = 1;
    codes = [0];
    $("#" + structure[0]).val(-1);
}


function getLevel(elementName) {
    for (var i = 0; i < structure.length; i++) {
        if (structure[i] === elementName)
            return i + 1;
    }
    return -1;
}

function isLastLevel(elementLevel) {
    return elementLevel === structure.length;
}

function deleteElements(nextElement) {
    for (var i = nextElement; i < structure.length; i++) {
        var element = document.getElementById(structure[i]);
        if (element !== null) {
            element.parentNode.remove(element);
        }
    }
}

function getCodes(elementLevel, elementVal) {

    if (elementLevel === 1) { //if root
        codes = [];
    } else if (elementLevel < level) { //if going back -> splice
        var len = codes.length;
        for (var i = elementLevel - 1; i < len; i++) {
            codes.pop();
        }
    }

    if (elementVal > 0) {
        codes.push(elementVal);
    }
}

function getCodeString() {
    var tmpCode = "";
    if(codes.length < 1){ //EMPTY ROOT 
        return "0";
    }
    for (var i = 0; i < codes.length; i++) {
        tmpCode += codes[i] + ",";
    }
    return tmpCode.substring(0, tmpCode.length - 1);
}

function populateTable(dataSet, columnsSet) {

    if (typeof territoryTable !== 'undefined') {
        territoryTable.clear().draw();
        territoryTable.rows.add(dataSet); // Add new data
        territoryTable.columns.adjust().draw(); // Redraw the DataTable
    } else {
        territoryTable = $('#territory-table').DataTable({
            data: dataSet,
            columns: columnsSet,
            responsive: true,
            lengthChange: false,
            pageLength: 15,
            order: [[0, "asc"]],
            buttons: ['csv', 'excel', 'pdf']
        });
        territoryTable.buttons().container().appendTo('#territory-table_wrapper .col-sm-6:eq(0)');
    }


}