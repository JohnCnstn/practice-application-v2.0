$(document).ready(function() {
    var assignTable =  $('#example1').DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "ordering": false,
        "info": false,
        "autoWidth": false,
        "colReorder": true
    });

    var assignStudentList = [];

    $('#example1 tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );

    $("#headMasterAssignButton").click(function(event) {

        var elements = document.getElementsByClassName('selected');

        for (var i = 0; i < elements.length; i++) {
            var element = elements[i];
            assignStudentList.push(element.id);
        }

        $(".selected").removeClass("selected");

        event.preventDefault();
        setOnPractice(assignStudentList);
    });

    function setOnPractice(assignStudentList){

        // PREPARE FORM DATA

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/assignOnPractice",
            data: JSON.stringify(assignStudentList),
            dataType: 'json',
            success: function (result) {
                callSuccessAlert('You assigned students!');
                console.log(result);
            },
            error: function (e) {
                callErrorAlert('One of students already on your practice!');
                console.log("ERROR: ", e);
            }
        });

        resetData();
    }

    function resetData(){
        assignStudentList = [];
    }
} );