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
    
    $safe_name = mysql_real_escape_string($_POST["menu"]);
    $safe_items = mysql_real_escape_string($_POST["items"]);
    
    $insertNew = "INSERT INTO menus (name, items) VALUES ('". $safe_name . "','". $safe_items . "')";
    if (!mysql_query($insertNew,$con)) {
        die('Error: ' . mysql_error());
    }
    echo "success" ;
    // close connection to database
    mysql_close($con) ;
?>