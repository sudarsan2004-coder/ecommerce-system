function calculateTotal() {
    let qty = document.getElementById("quantity").value;
    let rate = document.getElementById("rate").value;

    if (qty && rate) {
        document.getElementById("total").value = qty * rate;
    }
}
