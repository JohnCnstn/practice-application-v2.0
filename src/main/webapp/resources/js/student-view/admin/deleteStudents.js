$(document).ready(function() {

    var deleteStudentList = [];

    $("#deleteButton").click(function(event) {

        var elements = document.getElementsByClassName('selected');

        for (var i = 0; i < elements.length; i++) {
            var element = elements[i];
            deleteStudentList.push(element.id);
        }

        $(".selected").removeClass("selected");

        event.preventDefault();
        setOnPractice(deleteStudentList);
    });

    function setOnPractice(deleteStudentList){

        // PREPARE FORM DATA

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/deleteStudents",
            data: JSON.stringify(deleteStudentList),
            dataType: 'json',
            success: function (result) {
                callSuccessAlert('You deleted students!');
                console.log(result);
            },
            error: function (e) {
                callErrorAlert('You cant delete user!');
                console.log("ERROR: ", e);
            }
        });

        resetData();
    }

    function resetData(){
        assignStudentList = [];
    }
} );