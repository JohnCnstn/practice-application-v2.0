$(document).ready(function() {
    var table =  $('#example1').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": false,
        "autoWidth": false
    });

    $('#example1 tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );

    $('#button').click( function () {
        alert( table.rows('.selected').data().length +' row(s) selected' );
    } );
} );