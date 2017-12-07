$( document ).ready(function() {

    // SUBMIT FORM
    $("#practiceForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA

        var formData;

        formData = {
            startDate : $("#startDateAdmin").val(),
            endDate :  $("#endDateAdmin").val(),
            quantity :  $("#quantity").val(),
            headMasterId :  $("#headMasterId").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/postPractice",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (result) {
                $("#postResultDiv").html("<strong>Success</strong>");
                console.log(result);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });

        // Reset FormData after Posting
        resetData();
    }

    function resetData(){
        $("#startDateAdmin").val("");
        $("#endDateAdmin").val("");
        $("#quantity").val("");
    }
});
