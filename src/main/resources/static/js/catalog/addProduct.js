

addEventListenerToAllProductSizeDeleteButton();

let addButtonSize = document.querySelector("button.add__product__size__button");
addButtonSize.addEventListener('click', evt => {
        let lastFieldRow = document.querySelector("table.add__product__size__table tbody tr:last-child");
        lastFieldRow.insertAdjacentHTML("afterend", "<tr>\n" +
            "                                        <td>x</td>\n" +
            "                                        <td>\n" +
            "                                            <input type=\"number\" name=\"size\">\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <input type=\"number\" name=\"quantity\">\n" +
            "                                        </td>\n" +
            "                                        <td>\n" +
            "                                            <button type=\"button\" class=\"accent__button add__product__size__delete__button\">Видалити</button>\n" +
            "                                        </td>\n" +
            "                                    </tr>");
        updateFieldNumber();
        addEventListenerToAllProductSizeDeleteButton();
    }
);

function updateFieldNumber() {
    const fields = document.querySelectorAll("table.add__product__size__table tbody tr");
    let count = 1;
    fields.forEach(value1 => {
        value1.firstElementChild.textContent = count++;
    });
}

function deleteSizeField(value) {
    value.addEventListener('click', evt => {
        if (document.querySelectorAll("button.add__product__size__delete__button").length <= 1)
            return;
        value.parentElement.parentElement.remove();
        updateFieldNumber();
    });
}
function addEventListenerToAllProductSizeDeleteButton() {
    let deleteButtons = document.querySelectorAll("button.add__product__size__delete__button");

    deleteButtons.forEach(deleteSizeField);
}