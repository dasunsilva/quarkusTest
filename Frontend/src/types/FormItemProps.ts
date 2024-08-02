export interface FormItemProps {
  type: string;
  name: string;
  label: string;
  id: string;
  value: string | number | string[] | undefined;
  onChange: React.ChangeEventHandler<HTMLInputElement> | undefined;
}
