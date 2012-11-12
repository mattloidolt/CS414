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
    
    $unsafe_menu = $_POST['menu'] ;
    $safe_menu = mysql_real_escape_string($unsafe_menu);
    
    $insertNew = "DELETE FROM menus WHERE name='" . $safe_menu . "' ;" ;
    
    if (!mysql_query($insertNew,$con)) {
        echo $insertNew ;
        die('Error: ' . mysql_error());
    }
    echo "success" ;
    // close connection to database
    mysql_close($con) ;
?>