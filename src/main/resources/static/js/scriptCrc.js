function setCrcData(data) {
    const polynomial = document.querySelector('#polynomial');
    const initialValue = document.querySelector('#initialValue');
    const finalXorValue = document.querySelector('#finalXorValue');
    const inputReflected = document.querySelector('#inputReflected');
    const resultReflected = document.querySelector('#resultReflected');
    const bitSize = document.querySelectorAll('#customCrcParameters .form-check-input');

    polynomial.value = data.polynomial;
    initialValue.value = data.initialValue;
    finalXorValue.value = data.finalXorValue;
    inputReflected.checked = data.inputReflected;
    resultReflected.checked = data.resultReflected;
    data.bitLength === '16' ? bitSize[0].checked = true : bitSize[1].checked = true;
}

const postData = async (url, data) => {
    const res = await fetch(url, {
        method: "POST",
        headers: {
            'Content-type': 'application/json; charset=utf-8'
        },
        body: data
    });

    return await res.json();
};

const getResource = async (url) => {
    const res = await fetch(url);

    if (!res.ok) {
        throw new Error(`Could not fetch ${url}, status: ${res.status}`);
    }
    return await res.json();
};

window.addEventListener('DOMContentLoaded', function () {
    const crcTypeSelect = document.querySelector('#typeCRC');
    const predefinedCrc = document.querySelector('#predefinedCrc');
    const customCrc = document.querySelector('#customCrc');
    const fieldset = document.querySelectorAll('fieldset');

    getResource('http://localhost:8080/api/crc')
        .then(data => {
            crcTypeSelect.innerHTML = data.map(crc => `<option value=${crc.id}>${crc.name}</option>`).join('\n');
            setCrcData(data[0]);
            fieldset[0].disabled = false;
            fieldset[1].disabled = true;
        });

    crcTypeSelect.addEventListener('change', (e) =>{
        const crcId = e.target.value;
        getResource('http://localhost:8080/api/crc/' + crcId)
            .then(data => {
                setCrcData(data);
            });
    });

    predefinedCrc.addEventListener('click',(e) => {
        getResource('http://localhost:8080/api/crc/' + crcTypeSelect.value)
            .then(data => {
                setCrcData(data);
            });
        fieldset[0].disabled = false;
        fieldset[1].disabled = true;
    });

    customCrc.addEventListener('click',() => {
        fieldset[0].disabled = true;
        fieldset[1].disabled = false;
    });
});