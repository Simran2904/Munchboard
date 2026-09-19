let cartItems = [];


// ========================================
// ADD PRODUCT TO CART
// ========================================

function addProduct(name, price) {

    const existingItem = cartItems.find(
        item => item.name === name
    );

    if (existingItem) {

        existingItem.quantity++;

    } else {

        cartItems.push({
            name: name,
            price: price,
            quantity: 1
        });

    }

    updateCartCount();
    updateCart();

    alert(name + " added to cart 🛒");
}



// ========================================
// UPDATE CART COUNT
// ========================================

function updateCartCount() {

    let totalItems = 0;

    cartItems.forEach(item => {

        totalItems += item.quantity;

    });

    document.querySelector(".cart-count").textContent =
        totalItems;
}



// ========================================
// OPEN CART
// ========================================

function openCart() {

    document
        .getElementById("cartPanel")
        .classList.add("active");

    document
        .getElementById("cartOverlay")
        .classList.add("active");

}



// ========================================
// CLOSE CART
// ========================================

function closeCart() {

    document
        .getElementById("cartPanel")
        .classList.remove("active");

    document
        .getElementById("cartOverlay")
        .classList.remove("active");

}



// ========================================
// UPDATE CART DISPLAY
// ========================================

function updateCart() {

    const cartContainer =
        document.getElementById("cartItems");

    if (cartItems.length === 0) {

        cartContainer.innerHTML = `

            <div class="empty-cart">

                <div class="empty-cart-icon">
                    🛒
                </div>

                <h3>
                    Your cart is empty
                </h3>

                <p>
                    Add some delicious food to get started!
                </p>

            </div>

        `;

        document.getElementById("cartTotal")
            .textContent = "₹0";

        return;
    }


    let total = 0;

    cartContainer.innerHTML = "";


    cartItems.forEach((item, index) => {

        total += item.price * item.quantity;


        const itemElement =
            document.createElement("div");

        itemElement.className = "cart-item";


        itemElement.innerHTML = `

            <div class="cart-item-info">

                <h3>
                    ${item.name}
                </h3>

                <p>
                    ₹${item.price} × ${item.quantity}
                </p>

            </div>


            <button
                class="remove-button"
                onclick="removeFromCart(${index})"
            >
                Remove
            </button>

        `;


        cartContainer.appendChild(itemElement);

    });


    document.getElementById("cartTotal")
        .textContent = "₹" + total;

}



// ========================================
// REMOVE ITEM FROM CART
// ========================================

function removeFromCart(index) {

    cartItems.splice(index, 1);

    updateCartCount();

    updateCart();

}



// ========================================
// CHECKOUT
// ========================================

function checkout() {

    if (cartItems.length === 0) {

        alert("🛒 Your cart is empty!");

        return;
    }


    openCheckout();

}



// ========================================
// OPEN CHECKOUT
// ========================================

function openCheckout() {

    closeCart();

    document
        .getElementById("checkoutModal")
        .classList.add("active");

    generateBill();

}



// ========================================
// CLOSE CHECKOUT
// ========================================

function closeCheckout() {

    document
        .getElementById("checkoutModal")
        .classList.remove("active");

}



// ========================================
// PAYMENT METHOD
// ========================================

function selectPayment(method) {

    const codSection =
        document.getElementById("codSection");

    const upiSection =
        document.getElementById("upiSection");


    const codButton =
        document.getElementById("codButton");

    const upiButton =
        document.getElementById("upiButton");


    if (method === "cod") {

        codSection.style.display = "block";

        upiSection.style.display = "none";

        codButton.classList.add("selected");

        upiButton.classList.remove("selected");

    }


    if (method === "upi") {

        codSection.style.display = "none";

        upiSection.style.display = "block";

        codButton.classList.remove("selected");

        upiButton.classList.add("selected");

    }

}



// ========================================
// GENERATE BILL
// ========================================

function generateBill() {

    const billContainer =
        document.getElementById("billItems");

    const billTotal =
        document.getElementById("billTotal");


    billContainer.innerHTML = "";

    let total = 0;


    cartItems.forEach(item => {

        const itemTotal =
            item.price * item.quantity;

        total += itemTotal;


        const row =
            document.createElement("div");

        row.className = "bill-row";


        row.innerHTML = `

            <span>
                ${item.name}
                × ${item.quantity}
            </span>

            <strong>
                ₹${itemTotal}
            </strong>

        `;


        billContainer.appendChild(row);

    });


    billTotal.textContent = "₹" + total;

}



// ========================================
// PLACE ORDER
// ========================================

function placeOrder() {

    const name =
        document.getElementById("customerName").value.trim();

    const phone =
        document.getElementById("customerPhone").value.trim();

    const email =
        document.getElementById("customerEmail").value.trim();

    const address =
        document.getElementById("customerAddress").value.trim();


    if (
        name === "" ||
        phone === "" ||
        email === "" ||
        address === ""
    ) {

        alert(
            "Please fill in all customer details."
        );

        return;

    }


    const selectedPayment =
        document.querySelector(
            'input[name="paymentMethod"]:checked'
        );


    if (!selectedPayment) {

        alert(
            "Please select a payment method."
        );

        return;

    }


    if (selectedPayment.value === "upi") {

        alert(
            "📱 UPI payment selected.\n\n" +
            "This is a demo QR payment.\n\n" +
            "Your order will be processed after payment."
        );

    } else {

        alert(
            "🎉 Order placed successfully!\n\n" +
            "Payment Method: Cash on Delivery\n\n" +
            "Thank you for ordering from Munchboard!"
        );

    }


    cartItems = [];

    updateCartCount();

    updateCart();

    closeCheckout();

}