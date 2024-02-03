<?php
include "koneksi.php";

$id = rand(1000000000, 9999999999);
$nama = $_POST['nama'];
$jk = $_POST['jk'];
$alamat = $_POST['alamat'];
$keluhan = $_POST['keluhan'];
$nomor_handphone = $_POST['nomor_handphone'];

if  (isset($_POST['id_pasien'])) {
    $sql = "UPDATE pasien SET
        nama = '" . $nama . "',
        jk = '" . $jk . "',
        alamat = '" . $alamat . "',
        keluhan = '" . $keluhan . "',
        nomor_handphone = '" . $nomor_handphone . "'
        
        WHERE id_pasien = '" . $_POST['id_pasien'] . "'";
} else {


$sql = "INSERT INTO `pasien` (`no`, `id_pasien`, `nama`, `jk`, `alamat`, `keluhan`, `nomor_handphone`) 
VALUES (NULL, '" . $id . "', '" . $nama . "', '" . $jk . "', '" . $alamat . "', '" . $keluhan . "', '" . $nomor_handphone . "');";
}

$query = mysqli_query($db, $sql);
if ($query){
    echo json_encode(array('status' => 'data_tersimpan'));
}else{
    echo json_encode(array('status' => 'gagal_tersimpan'));
    }

