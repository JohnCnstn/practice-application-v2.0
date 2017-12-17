$( document ).ready(function() {

    // GET REQUEST
    $("#headMasterSetOnPractice").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url: window.location + "/headMasterAssignOnPractice",
            success: function (result) {
                callSuccessAlert('You assigned student!');
                console.log("Success: ", result);
            },
            error : function(e) {
                callErrorAlert('Student already on your practice!');
                console.log("ERROR: ", e);
            }
        });
    }
});