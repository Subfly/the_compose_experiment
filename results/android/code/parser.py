import os
import csv
import json

SOURCE_USER = "scroll"
SOURCE_AUTOMATED = "automated"

TEST_TYPE_FPS = "fps"
TEST_TYPE_MEMORY = "memory"

def merge_dicts(dict1, dict2):
    merged_dict = dict1.copy()

    for app_name, app_data in dict2.items():
        if app_name in merged_dict:
            for test_type, test_data in app_data.items():
                if test_type in merged_dict[app_name]:
                    for metric, metric_data in test_data.items():
                        merged_dict[app_name][test_type][metric] = {
                            **merged_dict[app_name][test_type].get(metric, {}),
                            **metric_data
                        }
                else:
                    merged_dict[app_name][test_type] = test_data
        else:
            merged_dict[app_name] = app_data

    return merged_dict

def parse_file(file_path):
    result = {}
    file_path_split = file_path.split('_')
    
    app_name = file_path_split[0][2:]
    test_source = SOURCE_USER if SOURCE_USER in file_path_split else SOURCE_AUTOMATED
    test_type = TEST_TYPE_FPS if TEST_TYPE_FPS in file_path_split else TEST_TYPE_MEMORY
    
    with open(file_path, 'r') as csv_file:
        csv_reader = csv.reader(csv_file)
        for row in csv_reader:
            step, measured = int(row[0]), float(row[1])
            if app_name not in result.keys():
                result[app_name] = {
                    test_source: {
                        test_type: {
                            step: measured
                        }
                    }
                }
            else:
                if test_source not in result[app_name].keys():
                    result[app_name][test_source] = {
                        test_type: {
                            step: measured
                        }
                    }
                else:
                    if test_type not in result[app_name][test_source].keys():
                        result[app_name][test_source][test_type] = {
                            step: measured
                        }
                    else:
                        result[app_name][test_source][test_type][step] = measured
    
    return result

def traverse_directory(directory_path):
    result = {}

    for root, _, files in os.walk(directory_path):
        for file_name in files:
            if file_name.endswith(".csv") and "AppSizes" not in file_name:
                file_path = os.path.join(root, file_name)
                parsed = parse_file(file_path)
                result = merge_dicts(parsed, result)

    return result

def save_to_json(data, output_file):
    with open(output_file, 'w') as json_file:
        json.dump(data, json_file, indent=4)

# Example usage:
if __name__ == "__main__":
    directory_path = "./"
    output_file = "./data.json"
    parsed_data = traverse_directory(directory_path)
    save_to_json(parsed_data, output_file)