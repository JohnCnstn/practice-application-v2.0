$( document ).ready(function() {

    // GET REQUEST
    $("#headMasterRemoveFromPractice").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url: window.location + "/headMasterRemoveFromPractice",
            success: function (result) {
                callDeleteAlert('You released student from practice!');
                console.log("Success: ", result);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
});