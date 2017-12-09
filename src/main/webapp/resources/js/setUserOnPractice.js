$( document ).ready(function() {

    // SUBMIT FORM
    $("#studentOnPracticeForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA

        var formData;

        var dataArrayToSend = [];
        dataArrayToSend.push("a");
        dataArrayToSend.push("b");
        dataArrayToSend.push("c");

        formData = {
            myArray : dataArrayToSend
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/postStudentOnPractice",
            data: JSON.stringify(dataArrayToSend),
            dataType: 'json',
            success: function (result) {
                $('#universityModal').modal('hide');
                console.log(result);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });
    }
});