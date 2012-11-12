<?php
    /**********************************************************************
     Start of PHP
     **********************************************************************/
    
    // establish connection
    $con = mysql_connect("faure.cs.colostate.edu","loidolt","827375410");
    if (!$con) {
        die('Could not connect: ' . mysql_error());
    }
    
    mysql_select_db("loidolt", $con);
    
    $q=mysql_query("SELECT id, name, items FROM orders");
    while($e=mysql_fetch_assoc($q))
        $output[]=$e;
    
    print(json_encode($output));
    
    // close connection to database
    mysql_close($con) ;


?>