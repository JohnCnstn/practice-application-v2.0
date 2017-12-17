$( document ).ready(function() {

    // SUBMIT FORM
    $("#customerForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA

        var formData;

        formData = {
            startDate : $("#startDate").val(),
            endDate :  $("#endDate").val(),
            quantity :  $("#quantity").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/postPractice",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (result) {
                $('#myModal').modal('hide');
                callSuccessAlert('You created practice!');
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
        $("#startDate").val("");
        $("#endDate").val("");
        $("#quantity").val("");
    }
});
