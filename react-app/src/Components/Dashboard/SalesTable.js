import React, { useEffect, useState } from 'react';

const SalesTable = props => {

    const [salesList, setSalesList] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [toUpdate, setToUpdate] = useState({});

    const handleDelete = (sale) => {
         const newList = [...salesList];
         const index = newList.indexOf(sale);
         newList.splice(index, 1);
         setSalesList(newList);

         SalesApis.deleteSale(sale.id);
    }

    useEffect( () => {
        SalesApis.getSales(setSalesList);
    }, [])

    return (
        <div style={{"marginTop": "20px"}}>
            {!showForm &&
                <table className='table' style={{'width': '80%', 'margin': 'auto'}}>
                    <thead>
                        <tr>
                            <th>Sale ID</th>
                            <th>Date of Sale</th>
                            <th>Order Status</th>
                            <th>Total Amount</th>
                            <th>Department ID</th>
                            <th>User ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        {salesList.map( s =>
                            <tr key={s.id}>
                                <td>{s.id}</td>
                                <td>{s.date_of_sale}</td>
                                <td>{s.order_status}</td>
                                <td>{s.total}</td>
                                <td>{s.dept_id}</td>
                                <td>{s.user_id}</td>
                                <td>
                                    <button className='btn btn-danger'
                                    onClick={() => handleDelete(s)}>
                                        Delete
                                    </button>
                                    <button className='btn btn-primary'
                                        onClick={() => {setToUpdate({'id': s.id, 'date_of_sale': s.date_of_sale, 'order_status': s.order_status, 
                                    'total': s.total, 'dept_id': s.dept_id, 'user_id': s.user_id}); setShowForm(true);}}>
                                        Update
                                    </button>
                                </td>
                            </tr>)}
                    </tbody>
                </table>}
                { showForm && <UpdateSaleForm toUpdate={toUpdate} setShowForm={setShowForm} setSalesList={setSalesList} />}
        </div>
    );
};

export default SalesTable;