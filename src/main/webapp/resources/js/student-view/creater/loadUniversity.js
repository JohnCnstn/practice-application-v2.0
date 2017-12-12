$( document ).ready(function() {

    // GET REQUEST
    $("#getAllUniversities").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url: window.location + "/getAllUniversities",
            success: function (result) {
                var html = '';
                var len = result.length;
                for(var i=0; i<len; i++){
                    html += '<option value="' + i + '">' + result[i].name + '</option>';
                }
                $("#getResultDiv").html(html);

                $('select#universityId').append(html);

                console.log("Success: ", result);
            },
            error : function(e) {
                $("#getResultDiv").html("<strong>Error</strong>");
                console.log("ERROR: ", e);
            }
        });
    }


    function getStates(){
        $.getJSON(
            "stateslist.html",
            {universityId: $('#universityId').val()},
            function(data) {
                var html = '';
                var len = data.length;
                for(var i=0; i<len; i++){
                    html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                }
                $('#state').append(html);
            }
        );
    }
});