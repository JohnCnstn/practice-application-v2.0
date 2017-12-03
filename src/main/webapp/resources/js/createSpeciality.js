$( document ).ready(function() {

    // SUBMIT FORM
    $("#specialityForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA

        var formData;

        formData = {
            name : $("#specialityName").val(),
            facultyId : $("#facultyId").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/postSpeciality",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (result) {
                if (result.status == "OK") {
                    $("#postResultDiv").html("<strong>Success</strong>");
                } else {
                    $("#postResultDiv").html("<strong>Error</strong>");
                }
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
        $("#specialityName").val("");
        $("#facultyId").val("");
    }
});
