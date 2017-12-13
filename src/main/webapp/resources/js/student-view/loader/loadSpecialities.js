$( document ).ready(function() {

    // GET REQUEST
    $("#getAllSpecialities").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url: window.location + "/getAllSpecialities",
            success: function (result) {
                var specialitiesList = '';
                var len = result.length;
                for(var i=0; i<len; i++){
                    specialitiesList += '<option value="' + result[i].id + '">' + result[i].name + '</option>';
                }
                $('select#specialityId').append(specialitiesList);
                console.log("Success: ", result);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
});