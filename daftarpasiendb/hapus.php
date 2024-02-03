<?php
include "koneksi.php";
$id_pasien = $_POST['id_pasien'];
$sql = "DELETE FROM pasien WHERE id_pasien='" . $id_pasien . "'";

$query = mysqli_query($db, $sql);
if ($query) {
    echo json_encode(array(
        'status' => 'data_berhasil_di_hapus'
    ));
} else {
    echo "gagal_hapus";
}