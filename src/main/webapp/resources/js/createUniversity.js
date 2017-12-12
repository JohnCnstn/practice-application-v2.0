$( document ).ready(function() {

    // SUBMIT FORM
    $("#universityForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA

        var formData;

        formData = {
            name : $("#universityName").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/postUniversity",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (data) {
                $('#universityModal').modal('hide');
                $('#universitySuccess').show();
                hideAlertOnTimeOut();
                console.log(data);
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
        $("#name").val("");
    }

    function hideAlertOnTimeOut(){
        window.setTimeout(function() {
            $('#universitySuccess').fadeTo(500, 0).slideUp(500, function(){
                $(this).remove();
            });
        }, 4000);
    }
});
