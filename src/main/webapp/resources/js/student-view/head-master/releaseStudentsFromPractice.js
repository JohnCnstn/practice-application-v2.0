$(document).ready(function() {

    var removeFromPracticeStudentList = [];

    $("#headMasterReleaseButton").click(function(event) {

        var elements = document.getElementsByClassName('selected');

        for (var i = 0; i < elements.length; i++) {
            var element = elements[i];
            removeFromPracticeStudentList.push(element.id);
        }

        $(".selected").removeClass("selected");

        event.preventDefault();
        removeFromPractice(removeFromPracticeStudentList);
    });

    function removeFromPractice(removeFromPracticeStudentList){
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/headMasterRemoveFromPractice",
            data: JSON.stringify(removeFromPracticeStudentList),
            dataType: 'json',
            success: function (result) {
                callSuccessAlert('You released students!');
                console.log(result);
            },
            error: function (e) {
                callErrorAlert(e.responseText);
                console.log("ERROR: ", e);
            }
        });

        resetData();
    }

    function resetData(){
        removeFromPracticeStudentList = [];
    }
} );