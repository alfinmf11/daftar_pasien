<?php
include "koneksi.php";
$sql = "UPDATE pasien
    SET
        nama = '" . $_POST['nama'] . "',
        jk = '" . $_POST['jk'] . "',
        alamat = '" . $_POST['alamat'] . "',
        keluhan = '" . $_POST['keluhan'] . "',
        nomor_handphone = '" . $_POST['nomor_handphone'] . "'
        WHERE id_pasien = '" . $_POST['id_pasien'] . "'";

    $query = mysqli_query($db, $sql);
    if($query){
        echo json_encode(array('status'=> 'data berhasil di update'));
    }