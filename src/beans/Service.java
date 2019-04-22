/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Stack;

/**
 *
 * @author Juan José Ramos
 */
public class Service {
    private int id;
    private String name;
    private String mark;
    private String model;
    private Stack sparePartList;
    private Double workPrice;
    private Double sparePartsPrice;
    private Double total;
    public Service next;
    public Service() {
    }

    public Service(int id, String name, String mark, String model, Stack sparePartList, Double workPrice, Double sparePartsPrice, Double total) {
        this.id = id;
        this.name = name;
        this.mark = mark;
        this.model = model;
        this.sparePartList = sparePartList;
        this.workPrice = workPrice;
        this.sparePartsPrice = sparePartsPrice;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Stack getSparePartList() {
        return sparePartList;
    }

    public void setSparePartList(Stack sparePartList) {
        this.sparePartList = sparePartList;
    }

    public Double getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(Double workPrice) {
        this.workPrice = workPrice;
    }

    public Double getSparePartsPrice() {
        return sparePartsPrice;
    }

    public void setSparePartsPrice(Double sparePartsPrice) {
        this.sparePartsPrice = sparePartsPrice;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

     

}
