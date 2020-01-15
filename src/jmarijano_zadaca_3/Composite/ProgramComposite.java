package jmarijano_zadaca_3.Composite;

import java.util.ArrayList;
import java.util.List;
import jmarijano_zacaca_3.Visitor.Visitor;
import jmarijano_zadaca_3.Decorator.ProgramDecoration;

public class ProgramComposite implements PlanComponent {

    private int id;
    private List<PlanComponent> children;
    private PlanComponent parent;

    @Override
    public PlanComponent getComponent() {

        return this;
    }

    public List<PlanComponent> getChildren() {
        return children;
    }

    public boolean add(PlanComponent planComponent) {
        if (children.add(planComponent)) {
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProgramComposite() {
        children = new ArrayList<>();
    }

    @Override
    public PlanComponent copy() {
        ProgramComposite programComposite = new ProgramComposite();
        programComposite.setId(this.id);
        programComposite.setChildren(this.children);
        programComposite.setParent(parent);
        return programComposite;
    }

    public void setChildren(List<PlanComponent> children) {
        this.children = children;
    }

    @Override
    public void showData() {
        if (this.children.size() > 0) {
            PlanComponent planComponent = new ProgramDecoration(this);
            planComponent.showData();
        }
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public PlanComponent getParent() {
        return parent;
    }

    public void setParent(PlanComponent parent) {
        this.parent = parent;
    }

}
