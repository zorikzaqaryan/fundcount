import React, {useState, useEffect} from "react";
import axios from 'axios'
import './App.css';

function App() {
    return (
        <div className="App">
            <Form/>
        </div>
    );
}

export default App;

function Form() {
    const [amount, setAmount] = useState("");
    const [selectedDate, setSelectedDate] = useState("");
    const [value, setValue] = useState("");
    const [response, setResponse] = useState("");

    useEffect(() => {
        function request() {
            let selected = new Date(selectedDate);
            let formatted_date = selected.getFullYear() + "-" + (selected.getMonth() + 1) + "-" + selected.getDate();
            return axios.get('http://localhost:8080/api/calculation?amount=' + amount + '&date=' + formatted_date)
                .then(response => {
                    // Handle service success response
                    setResponse('For $' + amount + '  calculated from ' + selectedDate + ' difference will be ' + response.data + ' RUB')
                });
        }

        value && request().then((res) => {
        });
    }, [value]);

    const submitHandler = (event) => {
        event.preventDefault();
        setValue(amount + selectedDate)
    };
    const changeHandler = setter => event => setter(event.target.value);
    return (
        <div>
            <form onSubmit={submitHandler}>
                <input
                    value={amount}
                    onChange={changeHandler(setAmount)}
                    placeholder="Amount"
                    type="number"
                    name="amount"
                    required
                />
                <input
                    value={selectedDate}
                    onChange={changeHandler(setSelectedDate)}
                    placeholder="Date"
                    type="date"
                    name="selectedDate"
                    required
                />
                <button type="submit">Recalculate</button>
            </form>
            <p>Fill amount and date and pres Recalculate</p>
            <span>{response}</span>

        </div>
    );
}

