package jmarijano_zadaca_3.FactoryMethod.ConcreteCreator;

import jmarijano_zadaca_3.FactoryMethod.ConcreteProduct.ProgramFileProduct;
import jmarijano_zadaca_3.FactoryMethod.Creator.FileCreator;
import jmarijano_zadaca_3.FactoryMethod.Product.FileProduct;

public class ProgramFileCreator implements FileCreator {

    @Override
    public FileProduct makeProduct() {
        return new ProgramFileProduct();
    }
}
