function navegarParaCima() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

function manipularString(field) {
    if (field == null) {
        return null;
    }
    field.value = field.value.replace(/\b\w/g, function(letter) {
        return letter.toUpperCase();
    });
}

function manipularStringSubmit() {
    var input = document.getElementById('formPessoa:nome');
    if (input != null) {
        manipularString(input);
    }
    return true;
}

