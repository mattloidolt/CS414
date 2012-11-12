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
    
    $safe_menu = mysql_real_escape_string($_POST["menu"]) ;
    
    $q=mysql_query("SELECT * FROM menus WHERE name='" . $safe_menu . "' ;");
    while($e=mysql_fetch_assoc($q))
        $output[]=$e;
    
    print(json_encode($output));
    
    // close connection to database
    mysql_close($con) ;
?>