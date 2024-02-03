<?php 
include "koneksi.php";
$sql = "SELECT * FROM pasien WHERE id_pasien = '" . $_POST['id_pasien'] . "'";

$query = mysqli_query($db, $sql);

$data = mysqli_fetch_assoc($query);
echo json_encode(array(
    'data' => array(
        'id_pasien' => $data['id_pasien'],
        'nama' => $data['nama'],
        'jk' => $data['jk'],
        'alamat' => $data['alamat'],
        'keluhan' => $data['keluhan'],
        'nomor_handphone' => $data['nomor_handphone'],
        )
    ));