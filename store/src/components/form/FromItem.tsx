import { FloatingLabel, Form } from "react-bootstrap";
import { FormItemProps } from "../../types/FormItemProps";

function FormItem({ type, name, label, value, id, onChange }: FormItemProps) {
  const FormLabelProps = {
    className: "mb-3",
    id: "inputField",
  };

  return (
    <FloatingLabel label={label} controlId={id} {...FormLabelProps}>
      <Form.Control
        type={type}
        name={name}
        value={value}
        placeholder=""
        onChange={onChange}
        required
      />
    </FloatingLabel>
  );
}

export default FormItem;
