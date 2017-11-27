$( document ).ready(function() {

    // SUBMIT FORM
    $("#headMasterForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA

        var formData;

        formData = {
            firstName : $("#firstName").val(),
            lastName : $("#lastName").val(),
            userName : $("#userName").val(),
            email : $("#email").val(),
            password : $("#password").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/postHeadMaster",
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
        $("#firstName").val("");
        $("#lastName").val("");
        $("#userName").val("");
        $("#email").val("");
        $("#password").val("");
    }
});
