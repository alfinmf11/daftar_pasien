<?php 
include "koneksi.php";

$sql = "SELECT * FROM pasien order by no desc";
$query = mysqli_query($db, $sql);
$list_data = array();
while ($data = mysqli_fetch_assoc($query)) {   
    $list_data[] = array(
        'id_pasien' => $data['id_pasien'],   
        'nama' => $data['nama'], 
        'jk' => $data['jk'],  
        'alamat' => $data['alamat'],  
        'keluhan' => $data['keluhan'],  
        'nomor_handphone' => $data['nomor_handphone']  
        );
    }
    echo json_encode(array(
        'data' => $list_data
    ));